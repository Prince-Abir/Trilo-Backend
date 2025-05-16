package payment_service.services;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import payment_service.dto.OrderDto;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderClient {
	
	@GetMapping("/trilo/order/{orderId}")
	OrderDto getOrderByOrderId(@PathVariable long orderId);
	
	
	@PutMapping("/trilo/order/{orderId}")
	OrderDto updateOrder(@RequestBody OrderDto Order, @PathVariable long orderId);

}
