package product_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import product_service.dto.ProductDto;
import product_service.dto.ProductVariantDto;
import product_service.entities.Product;
import product_service.entities.ProductVariant;
import product_service.exceptions.ProductNotFoundException;
import product_service.helper.ProductConverter;
import product_service.helper.ProductVariantConverter;
import product_service.repositories.ProductRepository;
import product_service.repositories.ProductVariantRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productDB;

	@Autowired
	private ProductVariantRepository productVariantRepository;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductVariantConverter productVariantConverter;

	@Override
	public ProductDto addProduct(ProductDto product) {

		Product p = productConverter.convertToEntity(product);
		Product savedProduct = productDB.save(p);
		return productConverter.convertToDto(savedProduct);
	}

	@Override
	public ProductDto getProduct(long productId) {
		Product product = productDB.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));
		return productConverter.convertToDto(product);
	}

	@Override
	public List<ProductDto> getAllProduct() {

		List<ProductDto> products = productDB.findAll().stream().map(product -> {
			return productConverter.convertToDto(product);

		}).collect(Collectors.toList());
		return products;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, long productId) {
		Product existingProduct = productDB.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + productId));

		// Clear old variants
		existingProduct.getProductVariants().clear();

		// Convert new product data
		Product updatedProduct = productConverter.convertToEntity(productDto);

		// Ensure ID is preserved
		existingProduct.setProductName(updatedProduct.getProductName());
		existingProduct.setProductDescription(updatedProduct.getProductDescription());
		existingProduct.setProductCategory(updatedProduct.getProductCategory());
		existingProduct.setProductRating(updatedProduct.getProductRating());
		existingProduct.setOnSale(updatedProduct.getOnSale());

		// Re-attach product to each variant and add to product
		for (ProductVariant variant : updatedProduct.getProductVariants()) {
			variant.setProduct(existingProduct);
			existingProduct.getProductVariants().add(variant);
		}

		Product savedProduct = productDB.saveAndFlush(existingProduct);

		return productConverter.convertToDto(savedProduct);
	}

	@Override
	public String deleteProduct(long productId) {

		Product product = productDB.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found!!"));
		productDB.delete(product);

		return "Product id " + productId + " deleted successfully";

	}

	@Override
	public ProductVariantDto getVariant(long varientId) {

		ProductVariant productVariant = productVariantRepository.findById(varientId)
				.orElseThrow(() -> new ProductNotFoundException("Product variant not found!!"));

		return productVariantConverter.convertToProductVariantDto(productVariant);

	}

}
