package com.orderService.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

	public static enum Status {
		PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;

	private long userId; // from user-service

	private long shippingAddressId;

	private long cartId;

	@Column(nullable = true)
	private Long paymentId; // #FF2HGU34LOUIO26E
	
	private String paymentMethod;

	@Enumerated(EnumType.STRING)
	private Status orderStatus; // PENDING, CONFIRMED, CANCEL, DELIVERED

	private LocalDateTime orderDate;

	private double totalAmount;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
	@JsonManagedReference()
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<OrderItem> items = new ArrayList<>();

}
