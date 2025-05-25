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

import shipping_service.dto.ShippingOrderDto;
import shipping_service.dto.ShippingRequest;
import shipping_service.services.ShippingOrderService;

@RestController
@RequestMapping(path = "/shipping/order")
public class ShippingOrderController {

	@Autowired
	private ShippingOrderService shippingOrderService;

	@PostMapping
	public ShippingOrderDto addShippingOrder(@RequestBody ShippingRequest order) {
		return shippingOrderService.addShippingOrder(order);

	}

	@GetMapping("/{shippingOrderId}")
	public ShippingOrderDto getShippingOrder(@PathVariable long shippingOrderId) {
		return shippingOrderService.getShippingOrder(shippingOrderId);

	}

	@GetMapping
	public List<ShippingOrderDto> getAllShippingOrders() {

		return shippingOrderService.getAllShippingOrders();

	}

	@PutMapping("/{shippingOrderId}")
	public ShippingOrderDto updateShippingOrder(@RequestBody ShippingRequest newOrder,
			@PathVariable long shippingOrderId) {

		return shippingOrderService.updateShippingOrder(newOrder, shippingOrderId);

	}

	@DeleteMapping("/{shippingOrderId}")
	public boolean deleteShippingOrder(@PathVariable long shippingOrderId) {

		return shippingOrderService.deleteShippingOrder(shippingOrderId);

	}

}
