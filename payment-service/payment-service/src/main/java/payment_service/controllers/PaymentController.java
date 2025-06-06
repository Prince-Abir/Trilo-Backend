package payment_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import payment_service.entities.Payment;
import payment_service.services.PaymentService;

@RestController
@RequestMapping("/trilo/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public String makePayment(@RequestBody Payment payment) {

		return paymentService.makePayment(payment);

	}

	@GetMapping("/{paymentId}")
	public Payment getPaymentDetailsById(@PathVariable long paymentId) {

		return paymentService.getPaymentDetailsById(paymentId);

	}
	
	@GetMapping
	public List<Payment> getAllPaymets() {

		return paymentService.getAllPayments();

	}
	
	
	
	@GetMapping("/user/{userId}")
	public List<Payment> getPaymentDetailsByUserId(@PathVariable long userId) {

		return paymentService.getPaymentDetailsByUserId(userId);

	}
	
	@GetMapping("/order/{orderId}")
	public Payment getPaymentDetailsByOrderId(@PathVariable long orderId) {

		return paymentService.getPaymentDetailsByOrderId(orderId);

	}


	@PutMapping("/{paymentId}")
	public Payment updatePayment(@RequestBody Payment newPayment, @PathVariable long paymentId) {

		return paymentService.updatePayment(newPayment, paymentId);

	}

	@DeleteMapping("/{paymentId}")
	public String deletePayment(@PathVariable long paymentId) {

		return paymentService.deletePayment(paymentId);

	}

}
