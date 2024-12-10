package com.demoProject.os.api.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.demoProject.os.api.common.Payment;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentService {

	@PostMapping("/payment/doPayment")
	Payment doPayment(Payment payment);

}