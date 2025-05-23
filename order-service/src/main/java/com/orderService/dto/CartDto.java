package com.orderService.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {
	
	private long cart_id;
	
	private long userId; // From user service;
	
	private double totalAmount;
	
	private String cartStatus;
	
	@JsonProperty("cartItems")
	private List<CartItemDto> cartItems = new ArrayList<>();
	
	

}

