package cart_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVariantDto {
	
	private long id;

	private String colorName;

	private String colorHex;
	
	private double price;

	private int quantity;

	private String size;

	private String imageUrl;
	
	private ProductDto product;

}
