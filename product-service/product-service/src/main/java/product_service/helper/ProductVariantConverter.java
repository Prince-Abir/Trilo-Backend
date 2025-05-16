package product_service.helper;

import org.springframework.stereotype.Component;

import product_service.dto.ProductVariantDto;
import product_service.entities.ProductVariant;

@Component
public class ProductVariantConverter {

	public ProductVariantDto convertToProductVariantDto(ProductVariant productVariant) {

		ProductVariantDto dto = new ProductVariantDto();

		dto.setId(productVariant.getId());
		dto.setColorName(productVariant.getColorName());
		dto.setColorHex(productVariant.getColorHex());
		dto.setPrice(productVariant.getPrice());
		dto.setQuantity(productVariant.getQuantity());
		dto.setSize(productVariant.getSize());
		dto.setImageUrl(productVariant.getImageUrl());

		return dto;

	}
	
	public ProductVariant convertToProductVariant(ProductVariantDto dto) {
	    ProductVariant productVariant = new ProductVariant();

	    productVariant.setId(dto.getId()); // Optional: only if you're updating
	    productVariant.setColorName(dto.getColorName());
	    productVariant.setColorHex(dto.getColorHex());
	    productVariant.setPrice(dto.getPrice());
	    productVariant.setQuantity(dto.getQuantity());
	    productVariant.setSize(dto.getSize());
	    productVariant.setImageUrl(dto.getImageUrl());

	    return productVariant;
	}


}
