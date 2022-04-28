package com.datastax.tutorials.service.order;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@PrimaryKeyClass
public class OrderByUserPrimaryKey {
	
    @PrimaryKeyColumn(
            name = "user_id", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
    private UUID userId;

	@PrimaryKeyColumn(
            name = "order_id", 
            ordinal = 1, 
            type = PrimaryKeyType.CLUSTERED,
			ordering = Ordering.DESCENDING)
	@CassandraType(type = Name.TIMEUUID)
	private UUID orderId;
        
	public UUID getUserId() {
		return userId;
	}
	
	public void setUserId(UUID userId) {
		this.userId = userId;
	}
	
	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
}
