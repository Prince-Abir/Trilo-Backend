package shipping_service.services;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shipping_service.entities.ShippingAddress;
import shipping_service.repository.ShippingAddressRepository;

@Service
public class ShippingAddressService {

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;

	public ShippingAddress addShippingAddress(ShippingAddress address) {

		return shippingAddressRepository.save(address);

	}

	public ShippingAddress getShippingAddress(long addressId) {

		return shippingAddressRepository.findById(addressId)
				.orElseThrow(() -> new RuntimeException("Address not found!"));

	}

	public List<ShippingAddress> getAllShippingAddress() {

		return shippingAddressRepository.findAll();
	}

	public ShippingAddress updateShippingAddress(ShippingAddress newAddress, long addressId) {

		ShippingAddress address = shippingAddressRepository.findById(addressId)
				.orElseThrow(() -> new RuntimeException("Address not found!"));

		if (address != null) {

			newAddress.setId(address.getId());
			return shippingAddressRepository.save(newAddress);

		}

		return null;

	}

	public boolean deleteShippingAddress(long addressId) {

		ShippingAddress address = shippingAddressRepository.findById(addressId)
				.orElseThrow(() -> new RuntimeException("Address not found!"));

		if (address != null) {
			shippingAddressRepository.delete(address);
			return true;

		}
		return false;

	}

}
