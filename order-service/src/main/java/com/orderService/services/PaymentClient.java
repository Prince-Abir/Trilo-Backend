package com.orderService.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orderService.dto.PaymentDto;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentClient {

	@GetMapping("/trilo/payment/user/{userId}")
	List<PaymentDto> getPaymentsofUser(@PathVariable long userId);

}
