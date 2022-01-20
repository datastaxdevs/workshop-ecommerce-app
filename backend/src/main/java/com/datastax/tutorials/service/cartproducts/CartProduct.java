package com.datastax.tutorials.service.cartproducts;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartProduct {
	
	@JsonProperty("cart_id")
	@NotNull(message="cart_id cannot be null")
	private UUID cartId;

	@JsonProperty("product_timestamp")
	@NotNull(message="product_timestamp cannot be null")
	private Date productTimestamp;

	@JsonProperty("product_id")
	@NotNull(message="product_id cannot be null")
	private String productId;
	
	@JsonProperty("product_description")
	private String productDesc;

	@JsonProperty("product_name")
	@NotNull(message="product_name cannot be null")
	private String productName;
	
	@Min(value=1, message="quantity must be greater than zero")
	@NotNull(message="quantity cannot be null")
	private int quantity;
	
	// Default constructor
	public CartProduct() {
		
	}
	
	/**
	 * Constructor.
	 * 
	 * @param cartId
	 *         cart identifier (UUID)
	 * @param productTimestamp
	 *         time product was added to the cart
	 * @param productId
	 *         product line identifier (UUID)
	 */	
	
	public UUID getCartId() {
		return cartId;
	}
	
	public void setCartId(UUID _cartId) {
		this.cartId = _cartId;
	}
	
	public Date getProductTimestamp() {
		return productTimestamp;
	}
	
	public void setProductTimestamp(Date _productTimestamp) {
		this.productTimestamp = _productTimestamp;
	}
	
	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String _productId) {
		this.productId = _productId;
	}
	
	public String getProductDesc() {
		return productDesc;
	}
	
	public void setProductDesc(String _productDesc) {
		this.productDesc = _productDesc;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String _productName) {
		this.productName = _productName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int _quantity) {
		this.quantity = _quantity;
	}
}
