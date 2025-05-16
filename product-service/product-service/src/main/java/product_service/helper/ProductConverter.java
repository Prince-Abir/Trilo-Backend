package product_service.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import product_service.dto.ProductDto;
import product_service.dto.ProductVariantDto;
import product_service.entities.Product;
import product_service.entities.ProductVariant;

@Component
public class ProductConverter {

	public Product convertToEntity(ProductDto productDto) {

		Product product = new Product();

		product.setProductId(productDto.getProductId());
		product.setProductName(productDto.getProductName());
		product.setProductDescription(productDto.getProductDescription());
		product.setProductCategory(productDto.getProductCategory());
		product.setProductRating(productDto.getProductRating());
		product.setOnSale(productDto.getOnSale());

		List<ProductVariant> productVariants = new ArrayList<>();

		productDto.getProductVariants().forEach(variant -> {

			ProductVariant productVariant = new ProductVariant(variant.getId(), variant.getColorName(),
					variant.getColorHex(), variant.getPrice(), variant.getQuantity(), variant.getSize(),
					variant.getImageUrl(), product);
			productVariants.add(productVariant);

		});

		product.setProductVariants(productVariants);

		return product;

	}

	public ProductDto convertToDto(Product product) {

		ProductDto productDto = new ProductDto();

		productDto.setProductId(product.getProductId());
		productDto.setProductName(product.getProductName());
		productDto.setProductDescription(product.getProductDescription());
		productDto.setProductCategory(product.getProductCategory());
		productDto.setProductRating(product.getProductRating());
		productDto.setOnSale(product.getOnSale());

		List<ProductVariantDto> productVariants = product.getProductVariants().stream().map(variant -> {
			ProductVariantDto productVariantDto = new ProductVariantDto(variant.getId(), variant.getColorName(),
					variant.getColorHex(), variant.getPrice(), variant.getQuantity(), variant.getSize(),
					variant.getImageUrl());

			return productVariantDto;

		}).collect(Collectors.toList());

		productDto.setProductVariants(productVariants);

		return productDto;

	}

}
