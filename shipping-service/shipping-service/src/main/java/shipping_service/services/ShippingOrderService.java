package shipping_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shipping_service.entities.ShippingOrder;
import shipping_service.repository.ShippingOrderRepository;

@Service
public class ShippingOrderService {

	@Autowired
	private ShippingOrderRepository shippingOrderRepository;

	public ShippingOrder addShippingOrder(ShippingOrder order) {

		return shippingOrderRepository.save(order);
	}

	public ShippingOrder getShippingOrder(long shippingOrderId) {
		return shippingOrderRepository.findById(shippingOrderId)
				.orElseThrow(() -> new RuntimeException("Shipping Order not found!!"));
	}

	public List<ShippingOrder> getAllShippingOrders() {
		return shippingOrderRepository.findAll();
	}

	public ShippingOrder updateShippingOrder(ShippingOrder newOrder, long shippingOrderId) {

		ShippingOrder order = shippingOrderRepository.findById(shippingOrderId)
				.orElseThrow(() -> new RuntimeException("Shipping Order not found!!"));

		newOrder.setShipmentId(order.getShipmentId());

		return shippingOrderRepository.save(newOrder);

	}

	public boolean deleteShippingOrder(long shippingOrderId) {
		ShippingOrder order = shippingOrderRepository.findById(shippingOrderId)
				.orElseThrow(() -> new RuntimeException("Shipping Order not found!"));

		shippingOrderRepository.delete(order);
		return true;

	}

}
