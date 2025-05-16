package product_service.services;

import java.util.List;

import product_service.dto.ProductDto;
import product_service.dto.ProductVariantDto;
import product_service.entities.ProductVariant;

public interface ProductService {
	
	public ProductDto addProduct(ProductDto product);
	
	public ProductDto getProduct(long productId);
	
	public List<ProductDto> getAllProduct();
	
	public ProductDto updateProduct(ProductDto product,long productId);
	
	public String deleteProduct(long productId);
	
	
	public ProductVariantDto getVariant(long varientId);

}
