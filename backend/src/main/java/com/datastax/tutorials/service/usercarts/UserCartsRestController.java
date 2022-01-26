package com.datastax.tutorials.service.usercarts;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Expose Rest Api to interact with user cart data. 
 *
 * @author Cedrick LUNVEN
 * @author Aaron PLOETZ 
 */
@RestController
@CrossOrigin(
  methods = {POST, GET, OPTIONS, PUT, DELETE, PATCH},
  maxAge = 3600,
  allowedHeaders = {"x-requested-with", "origin", "content-type", "accept"},
  origins = "*" 
)
@RequestMapping("/api/v1/users/")
@Tag(name = "User Cart Service", description="Provide crud operations for User Carts")
public class UserCartsRestController {

    /** Inject the repository. */
    private UserCartsRepository userCartRepo;
    
    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public UserCartsRestController(UserCartsRepository repo) {
        this.userCartRepo = repo;
    }
    
    /**
     * Retrieve all cart metadata for a user.
     * @param req
     *      current request
     * @param userid
     *      user identifier (UUID)
     * @return
     *      list of user carts
     */
    @GetMapping("/{userid}/carts")
    @Operation(
     summary = "Retrieve metadata for user carts by a userid",
     description= "Find **carts list** for a user by its id `SELECT * FROM user_carts WHERE user_id =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of carts is provided for the user",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = UserCart.class, name = "UserCart")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "userId not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check userId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Internal error.") 
    })
    public ResponseEntity<Stream<UserCart>> findAllByUserId(
            HttpServletRequest req, 
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)", example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {
        // Get the partition (be careful uniqueness is here not ensured
        List<UserCartEntity> e = userCartRepo.findByKeyUserId(userid);
        if (e.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(e.stream().map(this::mapUserCart));
    }

    /**
     * Retrieves current active cart metadata for a user.
     * @param req
     *      current request
     * @param userid
     *      user identifier (UUID)
     * @return
     *      (single) list of user cart
     */
    @GetMapping("/{userid}/carts?active=true")
    @Operation(
     summary = "Retrieve metadata for the user's active cart by a userid",
     description= "Find **carts list** for a user by its id `SELECT * FROM user_carts WHERE user_id =?`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "A list of carts is provided for the user",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = UserCart.class, name = "UserCart")
         )
       ),
       @ApiResponse(
         responseCode = "404", 
         description = "userId not found",
         content = @Content(mediaType = "")),
       @ApiResponse(
         responseCode = "400",
         description = "Invalid parameter check userId format."),
       @ApiResponse(
         responseCode = "500",
         description = "Internal error.") 
    })
    public ResponseEntity<Stream<UserCart>> findActiveCartByUserId(
            HttpServletRequest req, 
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)", example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {
    	// Create two lists of UserCartEntity
    	//  - One pulls the results from the database
        List<UserCartEntity> e = userCartRepo.findByKeyUserId(userid);
        //  - The other is empty, and will only contain the "active" cart.
        //    If there are no carts for the user, a new cart is created and returned.
        List<UserCartEntity> returnVal = new ArrayList<UserCartEntity>();
        if (e.isEmpty()) {
        	Date currDate = new Date();
        	
        	// create new cart with given userId (UUID)
        	UserCartEntity newCart = new UserCartEntity();
        	
        	// generate new key
        	UserCartsPrimaryKey key = new UserCartsPrimaryKey();
    		key.setUserId(userid);
    		key.setCartName("cart from " + currDate.toString());
    		key.setCartId(UUID.randomUUID());

        	newCart.setKey(key);
        	
        	// set new cart as active
        	newCart.setCartIsActive(true);
        	newCart.setUserEmail("no email specified");
        	
        	userCartRepo.save(newCart);
        	
        	// Add new cart to result set
        	returnVal.add(newCart);
    		
        } else {
        	// iterate through carts from DB
        	for (UserCartEntity cart : e) {
        		if (cart.getCartIsActive()) {
        			returnVal.add(cart);
        			break;
        		}
        	}
        }
        
        return ResponseEntity.ok(returnVal.stream().map(this::mapUserCart));
    }

    /**
     * Mapping Entity => REST.
     *
     * @param p
     *      entity
     * @return
     *      rest bean
     */
    private UserCart mapUserCart(UserCartEntity _uc) {
        UserCart uc = new UserCart();
        uc.setUserId(_uc.getKey().getUserId());
        uc.setCartName(_uc.getKey().getCartName());
        uc.setCartId(_uc.getKey().getCartId());
        uc.setCartIsActive(_uc.getCartIsActive());
        uc.setUserEmail(_uc.getUserEmail());
        return uc;
    }
}
