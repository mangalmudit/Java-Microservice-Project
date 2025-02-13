package com.demoProject.ps.api.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoProject.ps.api.Entity.Payment;
import com.demoProject.ps.api.repository.PaymentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	Logger logger= LoggerFactory.getLogger(PaymentService.class);

	public Payment doPayment(Payment payment) throws JsonProcessingException {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		 logger.info("Payment-Service Request : {}",new ObjectMapper().writeValueAsString(payment));
		return repository.save(payment);
	}

	public String paymentProcessing() {
		return new Random().nextBoolean() ? "success" : "false";

	}

	public Payment findPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
		Payment payment=repository.findByorderId(orderId);
        logger.info("paymentService findPaymentHistoryByOrderId : {}",new ObjectMapper().writeValueAsString(payment));
        return payment;
	}

}
