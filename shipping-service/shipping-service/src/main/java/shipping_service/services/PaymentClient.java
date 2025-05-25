package shipping_service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shipping_service.dto.PaymentDto;

@FeignClient("PAYMENT-SERVICE")
public interface PaymentClient {
	
	@GetMapping("/trilo/payment/order/{orderId}")
	PaymentDto getPaymentByOrderId(@PathVariable long orderId);

}
