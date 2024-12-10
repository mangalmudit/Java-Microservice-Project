package com.demoProject.os.api.common;

import com.demoProject.os.api.entity.Order;

import org.springframework.stereotype.Component;

@Component
public class TransationRequest {
	
	private Order order;
	private Payment payment;
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
}
