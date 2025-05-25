package shipping_service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shipping_service.dto.OrderDto;

@FeignClient("ORDER-SERVICE")
public interface OrderClient {
	
	
	@GetMapping("/trilo/order/{orderId}")
	OrderDto getOrderById(@PathVariable long orderId);
	
	
	

}
