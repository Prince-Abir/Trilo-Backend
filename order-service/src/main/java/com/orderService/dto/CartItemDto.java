package com.orderService.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {


	private long cartItemId;

	//private long cartId; // From Cart Class

	private long productVariantId; // From ProductVariant Class

	private String itemName;

	private String itemImageUrl;

	private String size;

	private String color;

	private int quantity;

	private double price;

	private double subTotal; // quantity * price

	
}
