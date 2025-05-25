package shipping_service.dto;

import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShippingRequest {
		
	public static enum Carrier {

		PATHAO, UBER, STEADFAST, LOCAL, PERSONAL
	}

	public static enum Status {

		PENDING, SHIPPED, DELIVERED
	}

	public static enum Area {

		IN_DHAKA, OUT_DHAKA
	}

	private Long orderId; // From Order Service

	private Long shippingAddressId; // From ShippingAddressDto;

	@Enumerated(EnumType.STRING)
	private Carrier carrier; // FedEx, DHL, etc.

	private String trackingNumber;

	@Enumerated(EnumType.STRING)
	private Area area;

	@Enumerated(EnumType.STRING)
	private Status ShipmentStatus; // Enum: PENDING, SHIPPED, DELIVERED

	private LocalDate estimatedDeliveryDate;

	private LocalDate shippedAt;

	private LocalDate deliveredAt;


}
