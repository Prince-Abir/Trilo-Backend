package user_service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user_service.entities.ShippingAddress;
import user_service.exceptions.UserNotFoundException;
import user_service.repository.ShippingAddressRepository;

@Service
public class ShippingAddressService {

	@Autowired
	private ShippingAddressRepository shippingAddressRepository;

	public ShippingAddress addShippingAddress(ShippingAddress address) {

		List<ShippingAddress> dbAddress = shippingAddressRepository.findByUserId(address.getUserId());

		if (dbAddress.isEmpty()) {
			return shippingAddressRepository.save(address);
		} else {
			throw new UserNotFoundException("Shipping Address found!");
		}

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

	public List<ShippingAddress> getShippingAddressByUserId(long userId) {

		List<ShippingAddress> address = shippingAddressRepository.findByUserId(userId);

		return address;
	}

}
