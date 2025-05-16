package payment_service.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payment_service.dto.OrderDto;
import payment_service.dto.UserDto;
import payment_service.entities.Payment;
import payment_service.repositories.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderClient orderClient;

	@Autowired
	private UserClient userClient;

	private static final Set<String> SUPPORTED_METHODS = Set.of("BKASH", "NAGAD", "UPAY", "ROCKET", "CARD",
			"CASH_ON_DELIVERY");
	private static final String DEFAULT_CURRENCY = "BDT";

	public String makePayment(Payment paymentRequest) {
		OrderDto order = new OrderDto();
		
		
		Optional<Payment> pp = paymentRepository.findByOrderIdAndPaymentStatus(Payment.Status.SUCCESS, paymentRequest.getOrderId());	
		if(pp.isPresent() || !pp.isEmpty()) {
			
			throw new RuntimeException("Order already sucessfully found");
			
		}
		
		
		
		
		order = orderClient.getOrderByOrderId(paymentRequest.getOrderId());

//		OrderDto order = 
		
		System.out.println("Request OrderId: " + paymentRequest.getOrderId());
		System.out.println("Fetched OrderId: " + order.getOrderId());


//---------------------Order Verification--------------------------------
		if (!(paymentRequest.getOrderId() == order.getOrderId())) {

			
			throw new RuntimeException("Order mismatch! 37");
		}

		if (String.valueOf(paymentRequest.getOrderId()) == null || String.valueOf(order.getOrderId()) == null) {

			throw new RuntimeException("Order not found! 42");

		}
		if (String.valueOf(paymentRequest.getOrderId()).isEmpty() || String.valueOf(order.getOrderId()).isEmpty()) {
			throw new RuntimeException("Order not found! 46");
		}

		if (order.getTotalAmount() <= 0) {
			throw new RuntimeException("Order is Violated!! 50");
		}

		if (!(order.getTotalAmount() == paymentRequest.getTotalAmount())) {

			throw new RuntimeException(" Amount Misamatch! 55");
		}

		// ---------------------User Verification--------------------------------

		UserDto user = userClient.getUserByUserId(paymentRequest.getUserId());

		if (user == null) {
			throw new RuntimeException("User not found! 63");
		}
		if (user.getUserAddress().isEmpty()) {
			throw new RuntimeException("Please add a Shipping Address... 66");
		}

		if (!(order.getUserId() == user.getUserId())) {
			throw new RuntimeException("Sorry Order and User Mismatch 70");

		}

		if (!SUPPORTED_METHODS.contains(paymentRequest.getPaymentMethod())) {
			throw new RuntimeException("Invalid Payment Method! 75");

		}
		if(paymentRequest.getPaymentMethod() == null || paymentRequest.getPaymentMethod().isEmpty()){
			throw new RuntimeException("Payment method not found!");
		}

		boolean p = paymentRepository.existsByTransactionId(paymentRequest.getTransactionId());

		if (p) {
			throw new RuntimeException("Transaction Found!! 82");

		}

		if (!paymentRequest.getPaymentMethod().toString().equalsIgnoreCase("COD")) {

			if (paymentRequest.getTransactionId().isEmpty() || paymentRequest.getTransactionId() == null) {
				throw new RuntimeException("Payment cannot be proceed! 89");

			}
		}
		if (!(paymentRequest.getPaymentStatus() == null)
				|| !(paymentRequest.getPaymentStatus().toString().isEmpty())) {

			Payment.Status status = paymentRequest.getPaymentStatus();

			if (status.equals(Payment.Status.SUCCESS) || status.equals(Payment.Status.PENDING)) {
				throw new RuntimeException("New Payment with existing id cannot be proceed! 99");
			}
			

		}else {
			throw new RuntimeException("Invalid payment Status!! 103");
		}

		if (!(order.getPaymentId() == null) && order.getPaymentId() == paymentRequest.getPaymentId()) {
			throw new RuntimeException("New Payment with existing id cannot be proceed! 107");
		}

		if (!paymentRequest.getCurrency().equals(DEFAULT_CURRENCY) && paymentRequest.getCurrency().isEmpty()) {
			throw new RuntimeException("Invalid Currency! 111");
		}

		
		paymentRequest.setPaymentStatus(Payment.Status.SUCCESS);
		Payment pay = paymentRepository.save(paymentRequest);
		if (pay != null) {
			order.setPaymentId(paymentRequest.getPaymentId());
			order.setPaymentMethod(paymentRequest.getPaymentMethod());
			OrderDto o = orderClient.updateOrder(order, order.getOrderId());
			if(o == null) {
				throw new RuntimeException("Order Update Failed!! Payment Service line 121");
			}

			return "Payment Successfull";
		}
		return "Payment Failed!";

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

}
