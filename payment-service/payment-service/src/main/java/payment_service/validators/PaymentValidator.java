package payment_service.validators;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import payment_service.dto.OrderDto;
import payment_service.dto.OrderDto.Status;
import payment_service.dto.UserDto;
import payment_service.entities.Payment;
import payment_service.exceptions.BadRequestException;
import payment_service.exceptions.DuplicateResourceFoundException;
import payment_service.exceptions.InternalServerException;
import payment_service.exceptions.JsonConversionException;
import payment_service.exceptions.ResourceNotFoundException;
import payment_service.repositories.PaymentRepository;
import payment_service.services.OrderClient;
import payment_service.services.UserClient;

@Component
public class PaymentValidator {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderClient orderClient;

	@Autowired
	private UserClient userClient;

	private static final Set<String> SUPPORTED_METHODS = Set.of("BKASH", "NAGAD", "UPAY", "ROCKET", "CARD", "COD");
	private static final String DEFAULT_CURRENCY = "BDT";

	public String validatePayment(Payment paymentRequest) {

		OrderDto order = new OrderDto();

		Optional<Payment> pp = paymentRepository.findByOrderIdAndPaymentStatus(Payment.Status.SUCCESS,
				paymentRequest.getOrderId());
		if (pp.isPresent() || !pp.isEmpty()) {

			throw new JsonConversionException("Order already on process");

		}

		order = orderClient.getOrderByOrderId(paymentRequest.getOrderId());

		if (order == null) {

			throw new BadRequestException("Order not found!");

		}

//---------------------Order Verification-------------------------------
		if (!(paymentRequest.getOrderId() == order.getOrderId())) {

			throw new BadRequestException("Order not found!");
		}

		if (String.valueOf(paymentRequest.getOrderId()) == null || String.valueOf(order.getOrderId()) == null) {

			throw new ResourceNotFoundException("Order not found!");

		}
		if (String.valueOf(paymentRequest.getOrderId()).isEmpty() || String.valueOf(order.getOrderId()).isEmpty()) {
			throw new BadRequestException("Order not found!");
		}
//
//		if (order.getTotalAmount() <= 0) {
//			throw new BadRequestException("Amount cannot be 0");
//		}

		if (!(order.getTotalAmount() == paymentRequest.getTotalAmount())) {

			throw new BadRequestException("Amount does not match!");
		}

		// ---------------------User Verification--------------------------------

		UserDto user = userClient.getUserByUserId(paymentRequest.getUserId());

		if (user == null) {
			throw new ResourceNotFoundException("user not found!");
		}
		if (user.getUserAddress().isEmpty()) {
			throw new ResourceNotFoundException("Shipping address not found!");
		}

		if (!(order.getUserId() == user.getUserId())) {
			throw new JsonConversionException("Order does not belongs to this user");

		}

		if (!SUPPORTED_METHODS.contains(paymentRequest.getPaymentMethod())) {
			throw new BadRequestException("Payment method not allowed!");

		}
		if (paymentRequest.getPaymentMethod() == null || paymentRequest.getPaymentMethod().isEmpty()) {
			throw new InternalServerException("Payment method not found!");
		}

		boolean p = paymentRepository.existsByTransactionId(paymentRequest.getTransactionId());

		if (p) {
			throw new InternalServerException("Transaction found!");

		}

		if (!paymentRequest.getPaymentMethod().toString().equalsIgnoreCase("COD")) {

			if (paymentRequest.getTransactionId().isEmpty() || paymentRequest.getTransactionId() == null) {
				throw new JsonConversionException("Invalid Payment transaction");
			}
		}
		if (!(paymentRequest.getPaymentStatus() == null) || !(paymentRequest.getPaymentStatus().toString().isEmpty())) {

			Payment.Status status = paymentRequest.getPaymentStatus();

			if (status.equals(Payment.Status.SUCCESS) || status.equals(Payment.Status.PENDING)) {
				throw new DuplicateResourceFoundException("Payment is Processing...");
			} 

		} else {
			throw new JsonConversionException("Invalid Payment Status");
		}

		if (!(order.getPaymentId() == null) && order.getPaymentId() == paymentRequest.getPaymentId()) {
			throw new JsonConversionException("Payment is Done..");
		}

		if (!paymentRequest.getCurrency().equals(DEFAULT_CURRENCY) && paymentRequest.getCurrency().isEmpty()) {
			throw new JsonConversionException("Invalid Currency! 111");
		}

		paymentRequest.setPaymentStatus(Payment.Status.SUCCESS);
		paymentRequest.setGatewayResponse("everything ok");
		paymentRequest.setUserId(order.getUserId());
		paymentRequest.setPaymentDateTime(LocalDateTime.now());

		Payment pay = paymentRepository.save(paymentRequest);

		if (pay != null) {

			order.setPaymentId(paymentRequest.getPaymentId());
			order.setPaymentMethod(paymentRequest.getPaymentMethod());
			order.setOrderStatus(Status.CONFIRMED);

			OrderDto o = orderClient.updateOrder(order, order.getOrderId());

			if (o == null) {
				throw new ResourceNotFoundException("Order update failed!");
			}

			return "Payment Successfull";
		}
		return "Payment Failed!";

	}

}
