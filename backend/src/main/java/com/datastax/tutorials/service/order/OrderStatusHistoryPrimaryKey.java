package com.datastax.tutorials.service.order;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class OrderStatusHistoryPrimaryKey {
	
	@PrimaryKeyColumn(
            name = "order_id", 
            ordinal = 0, 
            type = PrimaryKeyType.PARTITIONED)
	private UUID orderId;

	@PrimaryKeyColumn(
			name = "status_timestamp",
			ordinal = 1,
			type = PrimaryKeyType.CLUSTERED,
			ordering = Ordering.DESCENDING)
	private Date statusTimestamp;
	
	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}
	
	public Date getStatusTimestamp() {
		return statusTimestamp;
	}
	
	public void setStatusTimestamp(Date statusTimestamp) {
		this.statusTimestamp = statusTimestamp;
	}
}
