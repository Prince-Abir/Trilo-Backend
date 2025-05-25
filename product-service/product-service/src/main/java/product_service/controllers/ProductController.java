package product_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import product_service.dto.ProductDto;
import product_service.dto.ProductVariantDto;
import product_service.services.ProductServiceImpl;

@RestController
@RequestMapping("/trilo/products")
public class ProductController {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@PostMapping()
	public ProductDto addProduct(@RequestBody ProductDto product) {
		return productServiceImpl.addProduct(product);

	}

	@GetMapping("/{productId}")
	public ProductDto getProduct(@PathVariable long productId) {

		return productServiceImpl.getProduct(productId);

	}

	@GetMapping
	public List<ProductDto> getAllProduct() {

		return productServiceImpl.getAllProduct();

	}

	@PutMapping("/{productId}")
	public ProductDto updateProduct(@RequestBody ProductDto product, @PathVariable long productId) {
		return productServiceImpl.updateProduct(product, productId);

	}

	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable long productId) {
		return productServiceImpl.deleteProduct(productId);
	}

	@GetMapping("/{varientId}/variant")
	public ProductVariantDto getVariant(@PathVariable long varientId) {

		return productServiceImpl.getVariant(varientId);

	}

}
