package shipping_service.dto;

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
    
    private Boolean isDefault;

    
}
