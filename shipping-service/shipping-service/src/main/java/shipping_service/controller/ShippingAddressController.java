package shipping_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shipping_service.entities.ShippingAddress;
import shipping_service.services.ShippingAddressService;

@RestController
@RequestMapping(path = "/shipping")
public class ShippingAddressController {

	@Autowired
	private ShippingAddressService shippingAddressService;

	@PostMapping
	public ShippingAddress addShippingAddress(@RequestBody  ShippingAddress address) {
		return shippingAddressService.addShippingAddress(address);

	}

	@GetMapping("/{addressId}")
	public ShippingAddress getShippingAddress(@PathVariable long addressId) {
		return shippingAddressService.getShippingAddress(addressId);

	}

	@GetMapping
	public List<ShippingAddress> getAllShippingAddress() {

		return shippingAddressService.getAllShippingAddress();

	}

	@PutMapping("/{addressId}")
	public ShippingAddress updateShippingAddress(@RequestBody ShippingAddress newAddress,
			@PathVariable long addressId) {

		return shippingAddressService.updateShippingAddress(newAddress, addressId);

	}

	@DeleteMapping("/{addressId}")
	public boolean deleteShippingAddress(@PathVariable long addressId) {

		return shippingAddressService.deleteShippingAddress(addressId);

	}

}
