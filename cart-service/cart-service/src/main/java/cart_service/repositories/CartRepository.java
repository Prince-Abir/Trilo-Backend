package cart_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cart_service.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	
	List<Cart> findByUserId(long userId);
	

}
