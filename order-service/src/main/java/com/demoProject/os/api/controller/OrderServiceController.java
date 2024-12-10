package com.demoProject.os.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demoProject.os.api.common.TransactionResponse;
import com.demoProject.os.api.common.TransationRequest;
import com.demoProject.os.api.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.lang.*;

@RestController
@RequestMapping("/order")
public class OrderServiceController {

	@Autowired
	private OrderService service;

	@GetMapping("/{id}")
	public String welcomeMessage(@PathVariable Integer id ) {
		return "welcome to home page";
	}
	

	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransationRequest request) throws JsonProcessingException {
		TransactionResponse transactionResponse = service.saveOrder(request);
		return transactionResponse;

	}

}
