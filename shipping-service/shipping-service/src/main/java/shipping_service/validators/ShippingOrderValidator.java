package shipping_service.validators;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shipping_service.ShippingServiceApplication;
import shipping_service.dto.OrderDto;
import shipping_service.dto.PaymentDto;
import shipping_service.dto.ShippingAddressDto;
import shipping_service.dto.ShippingOrderDto;
import shipping_service.dto.ShippingRequest.Status;
import shipping_service.entities.ShippingOrder;
import shipping_service.entities.ShippingOrder.Area;
import shipping_service.entities.ShippingOrder.Carrier;
import shipping_service.exceptionHandlers.GlobalExceptionHandler;
import shipping_service.dto.ShippingRequest;
import shipping_service.exceptions.BadRequestException;
import shipping_service.exceptions.DuplicateResourceFoundException;
import shipping_service.exceptions.ResourceNotFoundException;
import shipping_service.helper.ShippingOrderConverter;
import shipping_service.repository.ShippingOrderRepository;
import shipping_service.services.OrderClient;
import shipping_service.services.PaymentClient;
import shipping_service.services.ShippingAddressClient;

@Component
public class ShippingOrderValidator {

	@Autowired
	private OrderClient orderClient;

	@Autowired
	private ShippingOrderRepository shippingOrderRepository;

	@Autowired
	private ShippingAddressClient shippingAddressClient;

	@Autowired
	private ShippingOrderConverter converter;

	@Autowired
	private PaymentClient paymentClient;

	public ShippingOrderDto createShippingOrder(ShippingRequest shippingOrder) {

		ShippingOrder ShippingOD = shippingOrderRepository.findByTrackingNumber(shippingOrder.getTrackingNumber());

		PaymentDto paymentDto = paymentClient.getPaymentByOrderId(shippingOrder.getOrderId());

		if (paymentDto == null || !"SUCCESS".contains(paymentDto.getPaymentStatus().toString())) {
			throw new ResourceNotFoundException("No payment found!!");
		}

		if (shippingOrder.getArea().name().contentEquals(Area.IN_DHAKA.toString())) {
			shippingOrder.setEstimatedDeliveryDate(LocalDate.now().plusDays(3));
		} else if (shippingOrder.getArea().name().contentEquals(Area.OUT_DHAKA.toString())) {
			shippingOrder.setEstimatedDeliveryDate(LocalDate.now().plusDays(10));
		} else {
			throw new BadRequestException("Sorry Invalid Area");
		}

		if (ShippingOD != null) {

			throw new DuplicateResourceFoundException("Shipping Order exist");

		}

		OrderDto order = orderClient.getOrderById(shippingOrder.getOrderId());

		if (order == null) {

			throw new ResourceNotFoundException("Order Not Found!");

		}

		if (order == null || !"CONFIRMED".contentEquals(order.getOrderStatus().toString())) {
			throw new ResourceNotFoundException("Payment issue...");

		}

		ShippingAddressDto shippingAddress = shippingAddressClient
				.getShippingAddressByAddressId(shippingOrder.getShippingAddressId());

		if (shippingAddress != null) {

			if (shippingAddress.getCity().equalsIgnoreCase("Dhaka")) {
				shippingOrder.setArea(shipping_service.dto.ShippingRequest.Area.IN_DHAKA);
			} else {
				shippingOrder.setArea(shipping_service.dto.ShippingRequest.Area.OUT_DHAKA);
			}

		} else {

			throw new ResourceNotFoundException("Invalid Shipping of User");
		}
		
		if(order.getUserId() != shippingAddress.getUserId()) {
			throw new ResourceNotFoundException("Shipping Address not belongs to the user");
		}
		
		if(paymentDto.getUserId() != order.getUserId()) {
			throw new ResourceNotFoundException("Valid Payment for Order not found!");
		}

		shippingOrder.setShipmentStatus(Status.SHIPPED);
		shippingOrder.setTrackingNumber(UUID.randomUUID().toString());
		shippingOrder.setDeliveredAt(null);
		shippingOrder.setShippedAt(LocalDate.now());

		ShippingOrder ShipmentOrder = new ShippingOrder();

		ShipmentOrder.setOrderId(shippingOrder.getOrderId());
		ShipmentOrder.setArea(Area.valueOf(shippingOrder.getArea().toString()));
		ShipmentOrder.setCarrier(Carrier.valueOf(shippingOrder.getCarrier().toString()));
		ShipmentOrder.setShippingAddressId(shippingOrder.getShippingAddressId());
		ShipmentOrder.setTrackingNumber(shippingOrder.getTrackingNumber());
		ShipmentOrder.setShipmentStatus(
				shipping_service.entities.ShippingOrder.Status.valueOf(shippingOrder.getShipmentStatus().toString()));

		ShipmentOrder.setDeliveredAt(shippingOrder.getDeliveredAt());
		ShipmentOrder.setEstimatedDeliveryDate(shippingOrder.getEstimatedDeliveryDate());
		ShipmentOrder.setShippedAt(shippingOrder.getShippedAt());

		ShippingOrder o = shippingOrderRepository.save(ShipmentOrder);

		return converter.toDTO(o);

	}

}
