package com.datastax.tutorials.service.usercarts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * WebBean to interact with UserCarts.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCart {

	@JsonProperty("user_id")
	@NotNull(message="user_id cannot be null")
	private UUID userId;
	
	@JsonProperty("cart_name")
	@NotNull(message="cart_name cannot be null")
	private String cartName;
	
	@JsonProperty("cart_id")
	@NotNull(message="cart_id cannot be null")
	private UUID cartId;
	
	@JsonProperty("cart_is_active")
	private boolean cartIsActive;
	
	@JsonProperty("user_email")
	private String userEmail;
	
	/**
	 * default constructor
	 */
	
	public UserCart() {
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param userId
	 *         user identifier (UUID)
	 * @param cartName
	 *         cart name
	 * @param cartId
	 *         cart identifier (UUID)
	 */	
	public UserCart(UUID _userId, String _cartName, UUID _cartId) {
		super();
		this.userId = _userId;
		this.cartName = _cartName;
		this.cartId = _cartId;
	}
	
	public UUID getUserId() {
		return this.userId;
	}
	
	public void setUserId(UUID _userId) {
		this.userId = _userId;
	}

	public String getCartName() {
		return this.cartName;
	}
	
	public void setCartName(String _cartName) {
		this.cartName = _cartName;
	}

	public UUID getCartId() {
		return this.cartId;
	}
	
	public void setCartId(UUID _cartId) {
		this.cartId = _cartId;
	}

	public boolean getCartIsActive() {
		return this.cartIsActive;
	}
	
	public void setCartIsActive(boolean _cartIsActive) {
		this.cartIsActive = _cartIsActive;
	}
	
	public String getUserEmail() {
		return this.userEmail;
	}
	
	public void setUserEmail(String _userEmail) {
		this.userEmail = _userEmail;
	}
}
