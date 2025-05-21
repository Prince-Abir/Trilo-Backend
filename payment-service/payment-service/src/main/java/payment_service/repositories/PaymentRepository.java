package payment_service.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import payment_service.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	
//	Optional<Payment> findByOrderIdAndPaymentStatusIn(long OrderId, List<Status>);
	
	boolean existsByTransactionId(String transactionId);
	
	@Query("SELECT p FROM Payment p WHERE p.paymentStatus = :paymentStatus AND p.orderId = :orderId")
	Optional<Payment> findByOrderIdAndPaymentStatus(@Param("paymentStatus") Payment.Status paymentStatus, @Param("orderId") long orderId);
	
	
	List<Payment> findByUserId(long userId);

	

}
