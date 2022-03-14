package com.datastax.tutorials.service.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.datastax.tutorials.service.user.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderByUser {

	@JsonProperty("user_id")
	@NotNull(message="user_id cannot be null")
	private UUID userId;

	@JsonProperty("order_id")
	@NotNull(message="order_id cannot be null")
	private UUID orderId;
	
	@JsonProperty("order_status")
	private String orderStatus;
	
	@JsonProperty("order_timestamp")
	private Date orderTimestamp;
	
	@JsonProperty("order_total")
	private BigDecimal orderTotal;
	
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderTimestamp() {
		return orderTimestamp;
	}

	public void setOrderTimestamp(Date orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
}
