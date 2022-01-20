package com.datastax.tutorials.service.cartproducts;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CartProductsPrimaryKey {
    @PrimaryKeyColumn(
            name = "cart_id", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED) 
	private UUID cartId;
 
    
    @PrimaryKeyColumn(
            name = "product_timestamp", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.DESCENDING)
    private Date productTimestamp;
    
    @PrimaryKeyColumn(
            name = "product_id", 
            ordinal = 2, 
            type = PrimaryKeyType.CLUSTERED, 
            ordering = Ordering.ASCENDING)
    private String productId;
	
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

	public UUID getCartId() {
    	return this.cartId;
    }
    
    public void setCartId(UUID _cartId) {
    	this.cartId = _cartId;
    }
}
