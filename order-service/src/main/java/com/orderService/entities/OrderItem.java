package com.orderService.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "order_item")
@Data
@NoArgsConstructor
public class OrderItem {
	
	public static enum Size {
	    S, M, L, XL, XXL
	}
	
	public static enum Color {
	    WHITE, BLACK, GREEN,YELLOW
	}

	@Id
	@Column(name = "order_item_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long productVariantId; // from product-variant

	private String itemName;

	private int quantity;

	@Enumerated(EnumType.STRING)
	private Size size; // S,M,L,XL,XXL

	@Enumerated(EnumType.STRING)
	private Color color; // White, Black, Green

	private double price;
	
	private String imageUrl;

	private double subTotal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	@JsonBackReference
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Order order;
	
}