package com.datastax.tutorials.service.usercarts;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

/**
 * CREATE TABLE user_carts (
 *	    user_id uuid,
 *	    cart_name text,
 *	    cart_id uuid,
 *	    cart_is_active boolean,
 *	    user_email text static,
 *	    PRIMARY KEY (user_id, cart_name, cart_id)
 */
@Table("user_carts")
public class UserCartEntity implements Serializable {
   
	private static final long serialVersionUID = -8897216351132633558L;

	@PrimaryKey
	private UserCartsPrimaryKey key;
	
	@Column("cart_is_active")
	@CassandraType(type = Name.BOOLEAN)
	private boolean cartIsActive;
	
	@Column("user_email")
	@CassandraType(type = Name.TEXT)
	private String userEmail;
	
	public UserCartsPrimaryKey getKey() {
		return this.key;
	}
	
	public void setKey(UserCartsPrimaryKey _key) {
		this.key = _key;
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
