package payment_service.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import payment_service.dto.UserDto;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {
	
	
	@GetMapping("trilo/user/{userId}")
	UserDto getUserByUserId(@PathVariable long userId);

}
