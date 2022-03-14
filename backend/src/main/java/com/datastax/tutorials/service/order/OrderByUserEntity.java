package com.datastax.tutorials.service.order;

import java.math.BigDecimal;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("order_by_user")
public class OrderByUserEntity {

	@PrimaryKey
	private OrderByUserPrimaryKey key;
	
	@Column("order_status")
	@CassandraType(type = Name.TEXT)
	private String orderStatus;

	@Column("order_total")
	@CassandraType(type = Name.DECIMAL)
	private BigDecimal orderTotal;

	public OrderByUserPrimaryKey getKey() {
		return key;
	}

	public void setKey(OrderByUserPrimaryKey key) {
		this.key = key;
	}

	public BigDecimal getOrderTimestamp() {
		return orderTotal;
	}

	public void setOrderTimestamp(BigDecimal orderTimestamp) {
		this.orderTotal = orderTimestamp;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
}
