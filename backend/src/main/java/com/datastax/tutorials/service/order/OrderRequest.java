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
public class OrderRequest {

	@JsonProperty("order_id")
	@NotNull(message="order_id cannot be null")
	private UUID orderId;
	
	@JsonProperty("product_list")
	@NotNull(message="product_list cannot be null")
	private List<OrderProduct> productList;
	
	@JsonProperty("order_status")
	private String orderStatus;
	
	@JsonProperty("order_timestamp")
	private Date orderTimestamp;
	
	@JsonProperty("order_subtotal")
	private BigDecimal orderSubtotal;
	
	@JsonProperty("order_shipping_handling")
	private BigDecimal orderShippingHandling;
	
	@JsonProperty("order_tax")
	private BigDecimal orderTax;
	
	@JsonProperty("order_total")
	private BigDecimal orderTotal;
	
	@JsonProperty("payment_method")
	private String paymentMethod;
	
	@JsonProperty("shipping_address")
	private Address shippingAddress;

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public List<OrderProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<OrderProduct> productList) {
		this.productList = productList;
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

	public BigDecimal getOrderSubtotal() {
		return orderSubtotal;
	}

	public void setOrderSubtotal(BigDecimal orderSubtotal) {
		this.orderSubtotal = orderSubtotal;
	}

	public BigDecimal getOrderShippingHandling() {
		return orderShippingHandling;
	}

	public void setOrderShippingHandling(BigDecimal orderShippingHandling) {
		this.orderShippingHandling = orderShippingHandling;
	}

	public BigDecimal getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(BigDecimal orderTax) {
		this.orderTax = orderTax;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
