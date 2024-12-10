package com.demoproject.cloud.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	
	@RequestMapping("/orderFallBack")
	public Mono<String> orderServiceFallBack(){
		return Mono.just("Order Service is taking too long to respond or is down. plz try agaim later ");
		
	}
	
	@RequestMapping("/paymentFallback")
	public Mono<String> paymentServiceFallBack()
	{
		return Mono.just("payment service is taking too long to respond or is down. Please try again later");
	}
	
	@GetMapping("/welcome")
	public String homePage()
	{
		return "Welcome to home screen";
	}

}
