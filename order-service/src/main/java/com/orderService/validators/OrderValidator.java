package com.orderService.validators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.orderService.dto.CartDto;
import com.orderService.dto.CartItemDto;
import com.orderService.dto.OrderDto;
import com.orderService.dto.OrderItemDto;
import com.orderService.dto.ShippingAddressDto;
import com.orderService.entities.Order;
import com.orderService.entities.OrderItem;
import com.orderService.entities.User;
import com.orderService.exceptions.JsonConversionException;
import com.orderService.exceptions.ResourceNotFoundException;
import com.orderService.repository.OrderRepository;
import com.orderService.services.CartClient;
import com.orderService.services.PaymentClient;
import com.orderService.services.UserClient;

@Component
public class OrderValidator {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private UserClient userClient;

	@Autowired
	private PaymentClient paymentClient;

	@Autowired
	private CartClient cartClient;

	private static final Set<String> SUPPORTED_METHODS = Set.of("BKASH", "NAGAD", "UPAY", "ROCKET", "CARD", "COD");

	private static final Set<String> status = Set.of("PENDING", "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED");

	public ResponseEntity<String> validateOrder(Order order) {

		if (order == null) {
			throw new JsonConversionException("Invalid Order Data!");
		}

		List<CartDto> carts = cartClient.getCartsByUserId(order.getUserId());
		for(CartDto cart: carts) {
			if(cart.getCartStatus().contentEquals("ACTIVE")) {
				System.out.println("Cart Found: "+cart);
			}
		}
		if(carts == null || carts.isEmpty()) {
			throw new ResourceNotFoundException("No Cart Found.");
		}
		
//		if(carts.isEmpty()) {
//			throw new ResourceNotFoundException("No Cart Found.");
//		}

		User user = userClient.getUserOfOrder((int) order.getUserId());
		if (Objects.isNull(user)) {
			throw new ResourceNotFoundException("Please register to proceed with the order.");
		}

		List<ShippingAddressDto> addresses = userClient.getShippingAddressByUserId(order.getUserId());
		if (addresses.isEmpty()) {
			throw new JsonConversionException("Please add a shipping address.");
		}

		// Set latest shipping address
		ShippingAddressDto latest = addresses.get(addresses.size() - 1);
		
		if(latest == null) {
			throw new JsonConversionException("Please add a shipping address.");
		}

		String method = order.getPaymentMethod();
		if (method == null || !SUPPORTED_METHODS.contains(method.toUpperCase())) {
			throw new ResourceNotFoundException("Invalid Payment Method");
		}

		CartDto activeCart = null;

		for (CartDto cart : carts) {
			if (cart != null && "ACTIVE".equalsIgnoreCase(cart.getCartStatus())) {
				activeCart = cart;
				break;
			}
		}
		
		if (activeCart == null) {
			throw new ResourceNotFoundException("Sorry no active cart found!");
		}

		if (activeCart.getCart_id() != order.getCartId()) {
			throw new ResourceNotFoundException("Invalid Cart Id");
		}

		// Perform your validation here with the active cart
		if (activeCart.getTotalAmount() != order.getTotalAmount()) {
			throw new ResourceNotFoundException("Cart Amount and Order Amount Mismatched!");
		} else if (activeCart.getCart_id() != order.getCartId()) {
			throw new ResourceNotFoundException("Cart and Order id Mismatched!");
		} else if (activeCart.getCartItems() == null) {
			throw new ResourceNotFoundException("No Cart items found!");
		}

		for (int i = 0; i < activeCart.getCartItems().size(); i++) {
			if (order.getItems().get(i).getSubTotal() != activeCart.getCartItems().get(i).getSubTotal()) {
				throw new ResourceNotFoundException("Sorry Inconsistent Amounts..");
			}
		}

		order.setCartId(activeCart.getCart_id());
		order.setOrderDate(LocalDateTime.now());
		order.setShippingAddressId(latest.getId());
		order.setTotalAmount(activeCart.getTotalAmount());

//		List<CartItemDto> it = activeCart.getCartItemDtos();
//		List<OrderItemDto> orderItems = new ArrayList<>();
//		
//		if (it != null)
//			for (int i = 0; i < it.size(); i++) {
//				
//				OrderItemDto dto = new OrderItemDto();
//
//				dto.setId(it.get(i).getCartItemId());
//				dto.setItemName(it.get(i).getItemName());
//				dto.setColor(OrderItemDto.Color.valueOf(it.get(i).getColor().toString()));
//				dto.setPrice(it.get(i).getPrice());
//				dto.setProductVariantId(it.get(i).getProductVariantId());
//				dto.setQuantity(it.get(i).getQuantity());
//				dto.setSize(OrderItemDto.Size.valueOf(it.get(i).getSize()));
//				dto.setSubTotal(it.get(i).getSubTotal());
//
//				orderItems.add(dto);
//
//			}
		
			
		
		List<OrderItem> orderItems = new ArrayList<>();
		for (CartItemDto cartItemDto : activeCart.getCartItems()) {
		    OrderItem orderItem = new OrderItem();
		    orderItem.setItemName(cartItemDto.getItemName());
		    orderItem.setColor(OrderItem.Color.valueOf(cartItemDto.getColor().toUpperCase()));
		    orderItem.setSize(OrderItem.Size.valueOf(cartItemDto.getSize().toUpperCase()));
		    orderItem.setQuantity(cartItemDto.getQuantity());
		    orderItem.setPrice(cartItemDto.getPrice());
		    orderItem.setProductVariantId(cartItemDto.getProductVariantId());
		    orderItem.setSubTotal(cartItemDto.getSubTotal());
		    orderItem.setImageUrl(cartItemDto.getItemImageUrl());
		    orderItem.setOrder(order); // Set the back-reference
		    orderItems.add(orderItem);
		}
		order.setItems(orderItems);


	
		
		activeCart.setCartStatus("INACTIVE");
		CartDto savedCart = cartClient.updateCartByCartId(activeCart, activeCart.getCart_id());

	
		order.setOrderStatus(
				order.getPaymentMethod().equalsIgnoreCase("COD") ? Order.Status.PENDING : Order.Status.CONFIRMED);

		order.setPaymentId(null);
		Order savedOrder = orderRepo.save(order);

		System.out.println(savedOrder);
		System.out.println(savedCart);

		return ResponseEntity.ok("Order Added Successfully ");
	}

}

//	public ResponseEntity<String> validateOrder(Order orderRequest) {
//
//		Order order = new Order();
//
//		if (orderRequest == null) {
//
//			throw new JsonConversionException("Invalid Order Data!");
//
//		}
//
//		User user = userClient.getUserOfOrder((int) orderRequest.getUserId());
//
//		if (Objects.isNull(user)) {
//
//			throw new ResourceNotFoundException("Please register to procced the order..");
//
//		} else {
//
//			List<ShippingAddressDto> shippingAddess = userClient.getShippingAddressByUserId(orderRequest.getUserId());
//
//			if (shippingAddess.isEmpty()) {
//
//				throw new JsonConversionException("Please add Shipping Address plssss");
//
//			} else {
//
//				orderRequest.setUserId(user.getUserId());
//
//				ShippingAddressDto add = shippingAddess.get(shippingAddess.size() - 1);
//				orderRequest.setShippingAddressId(add.getId());
//
//			}
//
//		}
//
//		//List<PaymentDto> payments = (ArrayList<PaymentDto>) paymentClient.getPaymentsofUser(orderRequest.getUserId());
//		//System.out.println(payments);
//
//		String paymentMethod = orderRequest.getPaymentMethod();
//
//		if (paymentMethod.isEmpty() || !SUPPORTED_METHODS.contains(paymentMethod)) {
//
//			throw new ResourceNotFoundException("Invalid Payment Method");
//
//		}
//
//		if (paymentMethod.contentEquals("COD")) {
//
//			order.setOrderStatus(Order.Status.PENDING);
//
//		} else {
//
//			order.setOrderStatus(Order.Status.CONFIRMED);
//
//		}
////			if (payments == null) {
////
////				throw new ResourceNotFoundException("No payment found!");
////
////			} else if (!((payments.get(payments.size() - 1)).getPaymentStatus().toString().toUpperCase()
////					.contentEquals("SUCCESS"))) {
////				throw new ResourceNotFoundException("Payment Status not success");
////			} else if (!((payments.get(0)).getTransactionId().isEmpty())) {
////				throw new ResourceNotFoundException("No transaction Found!");
////
////			} else if (!((payments.get(payments.size() - 1)).getTotalAmount() == orderRequest.getTotalAmount())) {
////				throw new ResourceNotFoundException("Amount mismatched!");
////			} else {
//
//		order.setPaymentId(null);
//
//		order.setUserId(user.getUserId());
//		order.setCartId(orderRequest.getCartId());
//		order.setShippingAddressId(orderRequest.getShippingAddressId());
//		order.setPaymentMethod(orderRequest.getPaymentMethod());
//
//		order.setOrderDate(orderRequest.getOrderDate());
//		order.setTotalAmount(orderRequest.getTotalAmount());
//
//		List<OrderItem> orderItems = orderRequest.getItems();
//
//		for (OrderItem item : orderItems) {
//			item.setOrder(order);
//		}
//		order.setItems(orderItems);
//
//		Order o = orderRepo.save(order);
//		System.out.println(o);
//		return ResponseEntity.ok("Order Added Successfully ");
//
//	}
