package shipping_service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import shipping_service.dto.ShippingAddressDto;

@FeignClient("USER-SERVICE")
public interface ShippingAddressClient {

	@GetMapping("/trilo/shipping/{addressId}")
	ShippingAddressDto getShippingAddressByAddressId(@PathVariable long addressId);

}
