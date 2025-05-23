package com.orderService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orderService.entities.Order;
import com.orderService.repository.OrderRepository;
import com.orderService.validators.OrderValidator;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private UserClient userClient;

	@Autowired
	private OrderValidator orderValidator;
	
	

	public ResponseEntity<String> createOrder(Order orderRequest) {

		return orderValidator.validateOrder(orderRequest);

	}

	
	public Order getOrder(long orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found!!"));

		return order;
	}

	public Order updateOrder(Order newOrder, long orderId) {

		Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found!!"));

		newOrder.setOrderId(order.getOrderId());

		return orderRepo.save(newOrder);
	}

}
