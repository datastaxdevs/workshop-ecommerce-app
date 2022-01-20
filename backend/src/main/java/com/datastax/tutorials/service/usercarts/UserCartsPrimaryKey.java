package com.datastax.tutorials.service.usercarts;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserCartsPrimaryKey {
	
    @PrimaryKeyColumn(
            name = "user_id", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private UUID userId;

    @PrimaryKeyColumn(
            name = "cart_name", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.ASCENDING)
    private String cartName;

    @PrimaryKeyColumn(
            name = "cart_id", 
            ordinal = 2, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.ASCENDING)
    private UUID cartId;


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
}
