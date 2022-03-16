package com.datastax.tutorials.service.order;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderStatusHistory {

	@JsonProperty("order_id")
	@NotNull(message="order_id cannot be null")
	private UUID orderId;
	
	@JsonProperty("status_timestamp")
	@NotNull(message="status_timestamp cannot be null")
	private Date statusTimestamp;

	@JsonProperty("order_status")
	private String orderStatus;

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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
