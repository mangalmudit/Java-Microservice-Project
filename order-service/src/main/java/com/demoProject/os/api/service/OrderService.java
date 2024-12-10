package com.demoProject.os.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demoProject.os.api.common.Payment;
import com.demoProject.os.api.common.TransactionResponse;
import com.demoProject.os.api.common.TransationRequest;
import com.demoProject.os.api.entity.Order;
import com.demoProject.os.api.external.services.PaymentService;
import com.demoProject.os.api.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@RefreshScope
public class OrderService {

	@Autowired
	private OrderRepository repository;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PaymentService paymentService;
	
	private static final String ORDER_SERVICE = "orderService";

	private Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;

	public TransactionResponse saveOrder(TransationRequest request) throws JsonProcessingException {
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());

		logger.info("OrderService request : {}", new ObjectMapper().writeValueAsString(request));
		//Payment paymentResponse = restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class);
		Payment paymentResponse =  sendPaymentRequest(payment);

		response = paymentResponse.getPaymentStatus().equals("success")
				? "payment processing successful and order placed"
				: "there is a oder failure in the payment service";

		logger.info("Order Service getting Response from Payment-Service : "
				+ new ObjectMapper().writeValueAsString(response));
		repository.save(order);

		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),
				response);
	}
	
	@CircuitBreaker(name =ORDER_SERVICE , fallbackMethod = "sendDefaultMessage")
	public Payment sendPaymentRequest(Payment payment) {
		 Payment paymentResponse =  paymentService.doPayment(payment);
		 return paymentResponse;
	}
	
	public Payment sendDefaultMessage(Exception e)
	{
		Payment paymentResponse = new Payment();
		paymentResponse.setAmount(0);
		paymentResponse.setPaymentStatus("There is issue in the payment service");
		paymentResponse.setPaymentId(0);
		paymentResponse.setTransactionId("99999999");
		return paymentResponse;
	}

}
