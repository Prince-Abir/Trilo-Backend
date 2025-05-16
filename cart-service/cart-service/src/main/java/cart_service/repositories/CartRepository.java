package cart_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cart_service.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	
	Cart findByUserId(long userId);
	

}
