package cart_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cart_service.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
