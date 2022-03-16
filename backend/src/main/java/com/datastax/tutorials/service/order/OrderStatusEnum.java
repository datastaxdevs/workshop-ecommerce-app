package com.datastax.tutorials.service.order;

public enum OrderStatusEnum {
	PENDING (0),
	PICKED (1),
	SHIPPED (2),
	COMPLETE (3),
	CANCELLED (4),
	ERROR (5);
	
	private int statusOrdinal;
	
	private OrderStatusEnum(int statusOrdinal) {
		this.statusOrdinal = statusOrdinal;
	}
	
	public int getStatusOrdinal() {
		return this.statusOrdinal;
	}
}