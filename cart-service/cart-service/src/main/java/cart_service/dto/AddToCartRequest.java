package cart_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddToCartRequest {
	
	private long userId;

	private long productVariantId;

	private String itemName;

	private String itemImageUrl;

	private String size;

	private String color;

	private int quantity;

	private double price;


}
