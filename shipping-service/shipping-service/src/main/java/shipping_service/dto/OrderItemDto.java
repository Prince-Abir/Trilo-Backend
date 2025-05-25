package shipping_service.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

	public static enum Size {
		S, M, L, XL, XXL
	}

	public static enum Color {
		WHITE, BLACK, GREEN, YELLOW
	}

	private long id;

	private long productVariantId; // from product-variant

	private String itemName;

	private int quantity;

	@Enumerated(EnumType.STRING)
	private Size size; // S,M,L,XL,XXL

	@Enumerated(EnumType.STRING)
	private Color color; // White, Black, Green

	private double price;

	private double subTotal;

//	private OrderDto orderDto;

}
