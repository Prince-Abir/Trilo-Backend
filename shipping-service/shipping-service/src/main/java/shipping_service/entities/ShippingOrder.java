package shipping_service.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "shipping_orders")
public class ShippingOrder {

	public static enum Carrier {

		PATHAO, UBER, STEADFAST, LOCAL, PERSONAL
	}

	public static enum Status {

		PENDING, SHIPPED, DELIVERED
	}

	public static enum Area {

		IN_DHAKA, OUT_DHAKA
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shipmentId;

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
