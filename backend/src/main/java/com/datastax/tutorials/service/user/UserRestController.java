package com.datastax.tutorials.service.user;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/v1/user/")
public class UserRestController {
	
    /** Inject the repositories. */
    private UserRepository userRepo;
    private UserByEmailRepository userByEmailRepo;
    
    /**
     * Injection through constructor.
     *  
     * @param repo
     *      repository
     */
    public UserRestController(UserRepository uRepo, UserByEmailRepository ueRepo) {
    	userRepo = uRepo;
    	userByEmailRepo = ueRepo;
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
    @Transactional()
    public ResponseEntity<User> user(@AuthenticationPrincipal OAuth2User principal) {
    	//Called by GitHub or Google login APIs
    	
    	UUID userId = null;
    	UserEntity user = null;
    	
    	//check if this is a returning user
    	// Both Google and GitHub have an "email" attribute
    	String email = principal.getAttribute("email");
    	Optional<UserByEmailEntity> existingUser = userByEmailRepo.findById(email);
    	
    	if (existingUser.isEmpty()) {
    		// If not, create new!
    		user = new UserEntity();
    		// need a userId, but we also need a way to get/transfer the one from the website
    		userId = UUID.randomUUID();
    		user.setUserId(userId);
    		user.setUserEmail(email);
    		
    		if (principal.getAttribute("family_name") != null) {
    			user.setLastName(principal.getAttribute("family_name"));
    		}
    		
    		if (principal.getAttribute("given_name") != null) {
    			user.setFirstName(principal.getAttribute("given_name"));
    		}
    		
           	if (email != null) {
           		UserByEmailEntity userByE = new UserByEmailEntity();
           		userByE.setUserEmail(email);
           		userByE.setUserId(userId);
           		userByEmailRepo.save(userByE);
           	}
           	
           	userRepo.save(user);
           	
    	} else {
	    	// existing user found!
	    	userId = existingUser.get().getUserId();
	        Optional<UserEntity> userO = userRepo.findById(userId);
	        
	        if (userO.isEmpty()) {
	        	// catch-all, if for whatever reason a valid userId can't yield an existing user
	            return ResponseEntity.notFound().build();
	        	
	        } else {
	        	// if it exists (it should) then invoke Optional's getter to convert from
	        	// Optional to User bean.
	        	user = userO.get();
	        }
    	}

       	//returnVal.put("name", principal.getAttribute("name"));
        return ResponseEntity.ok(mapUser(user));
    	//return returnVal;
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

    @GetMapping("/email/{email}")
    @Operation(
    	       summary = "Retrieve user by email",
    	       description= "Find User detail `SELECT * FROM user_by_email WHERE email=?`",
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
    public ResponseEntity<User> getUserByEmail(HttpServletRequest req, 
            @PathVariable(value = "email")
            @Parameter(name = "email", description = "email address", example = "bob.slydell@bobsconsulting.com")
            String email) {

    	Optional<UserByEmailEntity> userByEmail = userByEmailRepo.findById(email);
    	
    	if (userByEmail.isPresent()) {
    		// now pull the user data 
	        Optional<UserEntity> user = userRepo.findById(userByEmail.get().getUserId());
	        
	        if (user.isEmpty()) {
	        	// extra bullet proofing, just in case user is null (for whatever reason)
	            return ResponseEntity.notFound().build();
	        }
	        
	        return ResponseEntity.ok(mapUser(user.get()));
    	} else {
    		
    		return ResponseEntity.notFound().build();
    	}
    }
    
    @PostMapping("/{userid}/create")
    @Operation(
     summary = "Create user",
     description= "Creates a new user, saves it into the database",
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
    @Transactional
    public ResponseEntity<User> createUser(
            HttpServletRequest req, 
            @RequestBody User userData,
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)",
                       example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {
    	
    	String userEmail = userData.getUserEmail();
    	String userSessionId = userData.getSessionId();
    	
    	//check if this is a returning user
    	Optional<UserByEmailEntity> existingUser = userByEmailRepo.findById(userEmail);
    	
    	if (existingUser.isEmpty()) {
    		// If not, create new!
    		
	    	// Hash the password from the request body
	    	BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
	    	String hashedPassword = pEncoder.encode(userData.getPassword());
	    	
	    	// user save
	    	UserEntity userE = new UserEntity();
	    	//Required User properties
	    	userE.setUserId(userid);
	    	userE.setPassword(hashedPassword);
	       	userE.setPasswordTimestamp(new Date());
	       	userE.setUserEmail(userEmail);
	       	userE.setSessionId(userSessionId);
	
	       	// Optional User properties - check for null, first
	       	if (userData.getFirstName() != null) {
	       		userE.setFirstName(userData.getFirstName());
	       	}
	       	
	       	if (userData.getLastName() != null) {
	       		userE.setLastName(userData.getLastName());
	       	}
	       	
	       	if (userData.getLocale() != null) {
	           	userE.setLocale(userData.getLocale());       		
	       	}
	       	
	       	if (userData.getAddresses() != null ) {
	       		userE.setAddresses(mapAddressEntity(userData.getAddresses()));
	       	}
	       	
	       	// user_by_email save
	    	UserByEmailEntity userByEmailE = new UserByEmailEntity();
	    	userByEmailE.setUserId(userid);
	    	userByEmailE.setUserEmail(userEmail);
	        	
	    	// save to DB
	    	userRepo.save(userE);
	    	userByEmailRepo.save(userByEmailE);
	    	
	    	// return user data
	    	return ResponseEntity.ok(mapUser(userE));
    	} else {
    		Optional<UserEntity> userE = userRepo.findById(existingUser.get().getUserId());
    		
    		return ResponseEntity.ok(mapUser(userE.get()));
    	}
    }

    @PutMapping("/{userid}/update")
    @Operation(
     summary = "Update user data",
     description= "Updates an existing user in the database",
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
    @Transactional
    public ResponseEntity<User> updateUser(
            HttpServletRequest req, 
            @RequestBody User userData,
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)",
                       example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {
    	
    	boolean emailChanged = false;

    	// user save
    	UserEntity userE = new UserEntity();
    	
    	// userid is the partition key, always going to send that
    	userE.setUserId(userid);

    	// pull current sessionid
  		userE.setSessionId(req.getSession().getId());
    	
       	if (userData.getUserEmail() != null) {
       		emailChanged = true;
       		userE.setUserEmail(userData.getUserEmail());
       	}
       	
    	// check for null, as we only want to send data that's changed
    	if (userData.getPassword() != null) {
    		// Hash the password from the request body
    		BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
    		String hashedPassword = pEncoder.encode(userData.getPassword());
        	userE.setPassword(hashedPassword);
           	userE.setPasswordTimestamp(new Date());
    	}

       	// Optional User properties - check for null, first
       	if (userData.getFirstName() != null) {
       		userE.setFirstName(userData.getFirstName());
       	}
       	
       	if (userData.getLastName() != null) {
       		userE.setLastName(userData.getLastName());
       	}
       	
       	if (userData.getLocale() != null) {
           	userE.setLocale(userData.getLocale());       		
       	}
       	
       	if (userData.getAddresses() != null ) {
       		userE.setAddresses(mapAddressEntity(userData.getAddresses()));
       	}

       	if (emailChanged) {
       		// get old email
       		Optional<UserEntity> oldEmailEntry = userRepo.findById(userid);
       		String oldEmail = oldEmailEntry.get().getUserEmail();
       		
       		// user_by_email save
       		UserByEmailEntity userByEmailE = new UserByEmailEntity();
       		userByEmailE.setUserId(userid);
       		userByEmailE.setUserEmail(userData.getUserEmail());
        	userByEmailRepo.save(userByEmailE);
        	
        	// delete old email entry
        	userByEmailRepo.deleteById(oldEmail);
       	}
       	
    	// save to DB
    	userRepo.save(userE);
    	
    	// return user data
    	return ResponseEntity.ok(mapUser(userE));
    }
    
    /**
     * login with password (for those not using a 3rd party login).
     * @param req
     *      current request
     * @param userid
     *      user identifier (UUID)
     * @return
     *     user object
     */
    @PutMapping("/{userid}/login")
    @Operation(
     summary = "User login",
     description= "Allows the user to login to their account`",
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
    public ResponseEntity<Stream<User>> loginUser(
            HttpServletRequest req, 
            @RequestBody Password passwordData,
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)",
                       example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {

    	String rawPassword = passwordData.getPassword();
    	
    	// query user data
    	Optional<UserEntity> returnVal = userRepo.findById(userid);
    	
    	if (returnVal.isPresent()) {
        	String hashedPassword = returnVal.get().getPassword();
	    	// compare passwords
	    	BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
	    	if (pEncoder.matches(rawPassword, hashedPassword)) {
	    		// Match!  return user data
	        	return ResponseEntity.ok(returnVal.stream().map(this::mapUser));    		
	    	} else {
	    		// passwords do NOT match
	    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    	}
    	} else {
    		return ResponseEntity.notFound().build();
    	}
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
    @PostMapping("/{userid}/updatepassword")
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
            @RequestBody Password passwordData,
            @PathVariable(value = "userid")
            @Parameter(name = "userid", description = "user identifier (UUID)",
                       example = "5929e846-53e8-473e-8525-80b666c46a83")
            UUID userid) {

    	// Hash the password from the request body
    	BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
    	String oldPassword = passwordData.getPassword();
    	
    	// query user to verify that the old password matched what we have stored.
    	Optional<UserEntity> returnVal = userRepo.findById(userid);
    	
    	if (returnVal.isPresent()) {
	    	if (pEncoder.matches(oldPassword, returnVal.get().getPassword())) {
	    		// Match!  set new password
		    	String newHashedPassword = pEncoder.encode(passwordData.getNewPassword()); 
		    	
		    	UserEntity userE = returnVal.get();
		    	
		    	userE.setPassword(newHashedPassword);
		    	userE.setPasswordTimestamp(new Date());    	
		        	
		    	// save to DB
		    	userRepo.save(userE);
		    	
		    	// return user data
		    	return ResponseEntity.ok(returnVal.stream().map(this::mapUser));
	    	} else {
	    		// passwords do not match
	    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    	}
    	} else {
    		// user not found/returned, an error has occurred
    		return ResponseEntity.notFound().build();
    	}
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
        // shouldn't ever need to return this
        //u.setPassword(ue.getPassword());
        u.setSessionId(ue.getSessionId());
        u.setPasswordTimestamp(ue.getPasswordTimestamp());
        u.setSessionId(ue.getSessionId());

        if (ue.getAddresses() != null) {
            u.setAddresses(mapAddress(ue.getAddresses()));
        }
        
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
    
    /**
     * Mapping request UDT => Entity.
     *
     * @param a
     *      rest bean
     * @return
     *      entity
     */
    private List<AddressEntity> mapAddressEntity(List<Address> a) {

    	List<AddressEntity> addrList = new ArrayList<AddressEntity>();   	
    	for (Address addr : a) {
    		AddressEntity ae = new AddressEntity();
    		
    		ae.setType(addr.getType());
    		ae.setMailtoName(addr.getMailtoName());
    		ae.setStreet(addr.getStreet());
    		ae.setStreet2(addr.getStreet2());
    		ae.setCity(addr.getCity());
    		ae.setStateProvince(addr.getStateProvince());
    		ae.setPostalCode(addr.getPostalCode());
    		ae.setCountry(addr.getCountry());
    		
    		addrList.add(ae);
    	}
    	return addrList;
    }
}
