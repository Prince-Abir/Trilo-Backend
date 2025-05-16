package shipping_service.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shipmentId;
	
	private Long orderId; // From Order Service
	
	private Long shippingAddressId; // From ShippingAddress;
	
	private String carrier; // FedEx, DHL, etc.
	
	private String trackingNumber;
	
	private String shippingMethod; // Standard, Express, etc.
	
	private String ShipmentStatus; // Enum: PENDING, SHIPPED, DELIVERED
	
	private LocalDate estimatedDeliveryDate;
	
	private LocalDate shippedAt;
	
	private LocalDate deliveredAt;


}
