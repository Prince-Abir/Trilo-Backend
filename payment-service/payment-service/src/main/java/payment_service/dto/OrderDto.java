package payment_service.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderDto {

	public static enum Status {
		PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
	}

	private long orderId;

	private long userId;

	private long shippingAddressId;

	private long cartId;

	private Long paymentId; // #FF2HGU34LOUIO26E

	private String paymentMethod; // Cash on, Card, BKash

	@Enumerated(EnumType.STRING)
	private Status orderStatus; // PENDING, CONFIRMED, CANCEL, DELIVERED

	private LocalDateTime orderDate;

	private double totalAmount;

	private List<OrderItemDto> items = new ArrayList<>();

}
