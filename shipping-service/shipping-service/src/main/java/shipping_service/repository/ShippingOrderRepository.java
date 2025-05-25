package shipping_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shipping_service.entities.ShippingOrder;


@Repository
public interface ShippingOrderRepository extends JpaRepository<ShippingOrder, Long>{

	ShippingOrder findByTrackingNumber(String trackingNumber);
	
	

}
