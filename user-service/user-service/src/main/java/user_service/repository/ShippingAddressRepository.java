package user_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import user_service.dto.UserDTO;
import user_service.entities.ShippingAddress;


@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Long> {
	
	
	List<ShippingAddress> findByUserId(long userId);
	

}
