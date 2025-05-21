package payment_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payment_service.controllers.PaymentController;
import payment_service.entities.Payment;
import payment_service.repositories.PaymentRepository;
import payment_service.validators.PaymentValidator;

@Service
public class PaymentService {


	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentValidator paymentValidator;




	public String makePayment(Payment paymentRequest) {
		
		
		return paymentValidator.validatePayment(paymentRequest);
		


	}

	public Payment getPaymentDetailsById(long paymentId) {

		return paymentRepository.findById(paymentId)
				.orElseThrow(() -> new RuntimeException("Payment Details not found!! 132"));

	}

	public Payment updatePayment(Payment newPayment, long paymentId) {

		Payment oldPayment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new RuntimeException("Payment Details not found!! 139"));
		newPayment.setPaymentId(oldPayment.getPaymentId());

		return paymentRepository.save(newPayment);

	}

	public String deletePayment(long paymentId) {

		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new RuntimeException("Payment Details not found!!"));

		paymentRepository.delete(payment);

		return "Payment Info Deleted Successfully";

	}

	public List<Payment> getPaymentDetailsByUserId(long userId) {
		
		return paymentRepository.findByUserId(userId);
	}

	public List<Payment> getAllPayments() {
		
		return paymentRepository.findAll();
	}

}
