package shipping_service.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shipping_service.dto.ShippingOrderDto;
import shipping_service.dto.ShippingOrderDto.Area;
import shipping_service.dto.ShippingOrderDto.Carrier;
import shipping_service.dto.ShippingOrderDto.Status;
import shipping_service.dto.ShippingRequest;
import shipping_service.entities.ShippingOrder;
import shipping_service.helper.ShippingOrderConverter;
import shipping_service.repository.ShippingOrderRepository;
import shipping_service.validators.ShippingOrderValidator;

@Service
public class ShippingOrderService {

	@Autowired
	private ShippingOrderRepository shippingOrderRepository;

	@Autowired
	private ShippingOrderValidator shippingOrderValidator;

	@Autowired
	private ShippingOrderConverter converter;

	public ShippingOrderDto addShippingOrder(ShippingRequest order) {

		return shippingOrderValidator.createShippingOrder(order);
	}

	public ShippingOrderDto getShippingOrder(long shippingOrderId) {
		ShippingOrder order = shippingOrderRepository.findById(shippingOrderId)
				.orElseThrow(() -> new RuntimeException("Shipping Order not found!!"));

		return converter.toDTO(order);

	}

	public List<ShippingOrderDto> getAllShippingOrders() {

		List<ShippingOrder> shippingOrders = shippingOrderRepository.findAll();

		List<ShippingOrderDto> orders = shippingOrders.stream().map(e -> {

			ShippingOrderDto dto = converter.toDTO(e);
			return dto;

		}).collect(Collectors.toList());

		return orders;

	}

	public ShippingOrderDto updateShippingOrder(ShippingRequest newOrder, long shippingOrderId) {

		ShippingOrder order = shippingOrderRepository.findById(shippingOrderId)
				.orElseThrow(() -> new RuntimeException("Shipping Order not found!!"));

		ShippingOrderDto dto = converter.toDTO(order);
	
		
		dto.setShipmentId(shippingOrderId);
		dto.setOrderId(newOrder.getOrderId());
		dto.setArea(Area.valueOf(newOrder.getArea().name()));
		dto.setCarrier(Carrier.valueOf(newOrder.getCarrier().name()));
		dto.setShipmentStatus(Status.valueOf(newOrder.getShipmentStatus().name()));
		dto.setShippedAt(newOrder.getShippedAt());
		dto.setShippingAddressId(newOrder.getShippingAddressId());
		dto.setTrackingNumber(newOrder.getTrackingNumber());
		dto.setEstimatedDeliveryDate(newOrder.getEstimatedDeliveryDate());
		ShippingOrder o = shippingOrderRepository.save(converter.toEntity(dto));

		return converter.toDTO(o);

	}

	public boolean deleteShippingOrder(long shippingOrderId) {
		ShippingOrder order = shippingOrderRepository.findById(shippingOrderId)
				.orElseThrow(() -> new RuntimeException("Shipping Order not found!"));

		shippingOrderRepository.delete(order);
		return true;

	}

}
