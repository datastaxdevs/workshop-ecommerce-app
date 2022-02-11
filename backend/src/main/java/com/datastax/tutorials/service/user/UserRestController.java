package com.datastax.tutorials.service.user;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.tutorials.service.cartproducts.CartProduct;
import com.datastax.tutorials.service.cartproducts.CartProductEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Expose Rest Api to interact with users.
 *
 * @author Cedrick LUNVEN
 * @author Aaron PLOETZ
 */
@RestController
@CrossOrigin(
        methods = {POST,GET,OPTIONS,PUT,DELETE,PATCH}, 
        maxAge = 3600, 
        allowedHeaders = {"x-requested-with","origin","content-type","accept"}, 
        origins = "*")
@Tag(   name = "User Service", 
        description = "Provide crud operations for Users")
@RequestMapping("/api/v1/users/")
public class UserRestController {
	
    /** Inject the repository. */
    private UserRepository userRepo;
    
    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public UserRestController(UserRepository repo) {
    	userRepo = repo;
    }
    
    @GetMapping("/user")
    @Operation(
    	       summary = "Retrieve user by 3rd party login (Google, GitHub, etc)",
    	       description= "Find User detail",
    	       responses = {
    	         @ApiResponse(
    	           responseCode = "200",
    	           description = "A single user object will be returned",
    	           content = @Content(
    	            mediaType = "application/json",
    	                  schema = @Schema(implementation = User.class, name = "User")
    	                )
    	              ),
    	              @ApiResponse(
    	                responseCode = "404", 
    	                description = "Issue processing user, not found"),
    	              @ApiResponse(
    	                responseCode = "400",
    	                description = "Issue processing user, error occured"),
    	              @ApiResponse(
    	                responseCode = "500",
    	                description = "Internal server error.") 
    	    })
    public ResponseEntity<User> user(@AuthenticationPrincipal OAuth2User principal) {

    	UUID userId = null;
    	//check if this is a returning user
    	
    	// If not, create new!
    	
    	// If so, return!

    	//return Collections.singletonMap("name", principal.getAttribute("name"));
        Optional<UserEntity> user = userRepo.findById(userId);
        
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapUser(user.get()));
    }
    
    @GetMapping("/user/{userid}")
    @Operation(
    	       summary = "Retrieve user by user_id",
    	       description= "Find User detail `SELECT * FROM user WHERE user_id=?`",
    	       responses = {
    	         @ApiResponse(
    	           responseCode = "200",
    	           description = "A single user object will be returned",
    	           content = @Content(
    	            mediaType = "application/json",
    	                  schema = @Schema(implementation = User.class, name = "User")
    	                )
    	              ),
    	              @ApiResponse(
    	                responseCode = "404", 
    	                description = "Issue processing user, not found"),
    	              @ApiResponse(
    	                responseCode = "400",
    	                description = "Issue processing user, error occured"),
    	              @ApiResponse(
    	                responseCode = "500",
    	                description = "Internal server error.") 
    	    })
    public ResponseEntity<User> getUser(HttpServletRequest req, 
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)", example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {

        Optional<UserEntity> user = userRepo.findById(userid);
        
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapUser(user.get()));
    }
    
    /**
     * Set user password (for those not using a 3rd party login).
     * @param req
     *      current request
     * @param userid
     *      cart identifier (UUID)
     * @return
     *     user object
     *     
     *     Post vs. Put?
     *     
     *     https://security.stackexchange.com/questions/63604/put-vs-post-for-password-update
     *     
     *     On the other hand, a "POST" is the generic verb for API-like calls: 
     *     orders sent to a server for immediate execution. Such API calls are
     *     not idempotent. A "change password" call should probably be
     *     envisioned as an API call, not a file transfer. Therefore, you
     *     should use POST instead of PUT.
     *     
     */
    @PostMapping("/{userid}/setpassword")
    @Operation(
     summary = "Set password",
     description= "Allows the user to change their password `UPDATE user SET password=? WHERE userid=?;`",
     responses = {
       @ApiResponse(
         responseCode = "200",
         description = "Update completed",
         content = @Content(
           mediaType = "application/json",
           schema = @Schema(implementation = User.class, name = "user")
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
    public ResponseEntity<Stream<User>> updateUserPassword(
            HttpServletRequest req, 
            @RequestBody User newUserData,
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)", example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {

    	// Hash the password from the request body
    	BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
    	String hashedPassword = pEncoder.encode(newUserData.getPassword());
    	
    	UserEntity userE = new UserEntity();
    	userE.setUserId(userid);
    	userE.setPassword(hashedPassword);
       	userE.setPasswordDate(new Date());    	
    	
    	// save to DB
    	userRepo.save(userE);
    	
    	// return current cart contents
    	Optional<UserEntity> returnVal = userRepo.findById(userid);
    	
    	return ResponseEntity.ok(returnVal.stream().map(this::mapUser));
    }
    
    /**
     * Mapping Entity => REST.
     *
     * @param ue
     *      entity
     * @return
     *      rest bean
     */
    private User mapUser(UserEntity ue) {
        User u = new User();

        u.setUserId(ue.getUserId());
        u.setUserEmail(ue.getUserEmail());
        u.setPictureUrl(ue.getPictureUrl());
        u.setFirstName(ue.getFirstName());
        u.setLastName(ue.getLastName());
        u.setLocale(ue.getLocale());
        u.setAddresses(mapAddress(ue.getAddresses()));
        u.setPassword(ue.getPassword());
        u.setTokentxt(ue.getTokentxt());
        
        return u;
    }
    
    /**
     * Mapping Entity UDT => REST.
     *
     * @param ae
     *      entity
     * @return
     *      rest bean
     */
    private List<Address> mapAddress(List<AddressEntity> ae) {

    	List<Address> addrList = new ArrayList<Address>();   	
    	for (AddressEntity addrE : ae) {
    		Address a = new Address();
    		
    		a.setType(addrE.getType());
    		a.setMailtoName(addrE.getMailtoName());
    		a.setStreet(addrE.getStreet());
    		a.setStreet2(addrE.getStreet2());
    		a.setCity(addrE.getCity());
    		a.setStateProvince(addrE.getStateProvince());
    		a.setPostalCode(addrE.getPostalCode());
    		a.setCountry(addrE.getCountry());
    		
    		addrList.add(a);
    	}
    	return addrList;
    }
}
