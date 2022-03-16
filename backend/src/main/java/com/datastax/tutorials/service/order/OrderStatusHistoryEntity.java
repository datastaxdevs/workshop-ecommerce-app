package com.datastax.tutorials.service.order;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("order_status_history")
public class OrderStatusHistoryEntity implements Serializable {

	private static final long serialVersionUID = 5329800173538611720L;

	@PrimaryKey
	private OrderStatusHistoryPrimaryKey key;
	
	@Column("order_status")
	private String orderStatus;

	public OrderStatusHistoryPrimaryKey getKey() {
		return key;
	}

	public void setKey(OrderStatusHistoryPrimaryKey key) {
		this.key = key;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
