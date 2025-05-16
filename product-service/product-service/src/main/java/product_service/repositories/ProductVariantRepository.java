package product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import product_service.entities.ProductVariant;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long>{

}
