package com.orderService.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShippingAddressDto {

    private Long id;

    private Long userId; // To associate with the user (from User Service)

    private String recipientName;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private String primaryPhone;
    
    private String secondaryPhone;
    
    @Column(name = "is_default")
    private Boolean isDefault;

    
}
