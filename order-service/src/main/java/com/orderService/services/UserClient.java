package com.orderService.services;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.orderService.dto.ShippingAddressDto;
import com.orderService.entities.User;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {
	
	
	@GetMapping("/trilo/user/{userId}")
	User getUserOfOrder(@PathVariable int userId);
	
	@GetMapping("/trilo/shipping/user/{userId}")
	List<ShippingAddressDto> getShippingAddressByUserId(@PathVariable long userId);
}
