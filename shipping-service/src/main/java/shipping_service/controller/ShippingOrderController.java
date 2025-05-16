package shipping_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shipping_service.entities.ShippingOrder;
import shipping_service.services.ShippingOrderService;

@RestController
@RequestMapping(path = "/shipping/order")
public class ShippingOrderController {

	@Autowired
	private ShippingOrderService shippingOrderService;

	@PostMapping
	public ShippingOrder addShippingOrder(@RequestBody ShippingOrder order) {
		return shippingOrderService.addShippingOrder(order);

	}

	@GetMapping("/{shippingOrderId}")
	public ShippingOrder getShippingOrder(@PathVariable long shippingOrderId) {
		return shippingOrderService.getShippingOrder(shippingOrderId);

	}

	@GetMapping
	public List<ShippingOrder> getAllShippingOrders() {

		return shippingOrderService.getAllShippingOrders();

	}

	@PutMapping("/{shippingOrderId}")
	public ShippingOrder updateShippingOrder(@RequestBody ShippingOrder newOrder, @PathVariable long shippingOrderId) {

		return shippingOrderService.updateShippingOrder(newOrder, shippingOrderId);

	}

	@DeleteMapping("/{shippingOrderId}")
	public boolean deleteShippingOrder(@PathVariable long shippingOrderId) {

		return shippingOrderService.deleteShippingOrder(shippingOrderId);

	}

}
