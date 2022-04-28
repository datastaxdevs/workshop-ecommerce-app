package com.datastax.tutorials.service.order;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@PrimaryKeyClass
public class OrderPrimaryKey {
    @PrimaryKeyColumn(
            name = "order_id", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = Name.TIMEUUID)
	private UUID orderId;
 
    @PrimaryKeyColumn(
            name = "product_name", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED) 
    private String productName;
    
    @PrimaryKeyColumn(
            name = "product_id", 
            ordinal = 2, 
            type = PrimaryKeyType.CLUSTERED)
    private String productId;

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}
