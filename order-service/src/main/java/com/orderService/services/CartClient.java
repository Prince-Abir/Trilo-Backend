package com.orderService.services;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.orderService.dto.CartDto;


@FeignClient(name="CART-SERVICE")
public interface CartClient {
	
	
	@GetMapping("/trilo/cart/user/{userId}")
	List<CartDto> getCartsByUserId(@PathVariable long userId);
	
	@PutMapping("/trilo/cart/{cartId}")
	CartDto updateCartByCartId(@RequestBody CartDto newCart,@PathVariable long cartId);

	@GetMapping("/trilo/cart/{cartId}")
	CartDto getCartById(@PathVariable long cartId);

}
