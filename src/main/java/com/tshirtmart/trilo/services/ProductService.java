package com.tshirtmart.trilo.services;

import java.util.List;

import com.tshirtmart.trilo.dto.ProductDto;

public interface ProductService {
	
	public ProductDto addProduct(ProductDto product);
	
	public ProductDto getProduct(long productId);
	
	public List<ProductDto> getAllProduct();
	
	public ProductDto updateProduct(ProductDto product,long productId);
	
	public String deleteProduct(long productId);

}
