package com.orderService.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDto {
	
	
	public static enum Status {
		PENDING, SUCCESS, FAILED, CANCELLED
	}

	private long paymentId;
	
	private long userId;
	
	private long orderId;
	
	private String paymentMethod;
	
	private String transactionId;
	
	private double totalAmount;
	
	@Enumerated(EnumType.STRING)
	private Status paymentStatus;
	
	private LocalDateTime paymentDateTime;
	
    private String gatewayResponse; // Raw JSON/text from external payment gateway (if needed)

    private String currency; // Default: BDT, USD, etc.

    private String remarks; // Optional note or reason for failure

	
	

}
