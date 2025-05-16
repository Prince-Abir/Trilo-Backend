package cart_service.feingClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cart_service.dto.ProductVariantDto;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductVariantClient {
	
	
	@GetMapping("/trilo/product/{varientId}/variant")
	ProductVariantDto getProductVariant(@PathVariable long varientId);

}
