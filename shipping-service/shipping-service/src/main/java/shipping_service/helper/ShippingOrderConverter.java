package shipping_service.helper;

import org.springframework.stereotype.Component;

import shipping_service.dto.ShippingOrderDto;
import shipping_service.dto.ShippingOrderDto.Area;
import shipping_service.dto.ShippingOrderDto.Carrier;
import shipping_service.dto.ShippingOrderDto.Status;
import shipping_service.entities.ShippingOrder;

@Component
public class ShippingOrderConverter {

	    public ShippingOrderDto toDTO(ShippingOrder entity) {
	        if (entity == null) return null;
	        
	        ShippingOrderDto dto = new ShippingOrderDto();
	        dto.setShipmentId(entity.getShipmentId());
	        dto.setOrderId(entity.getOrderId());
	        dto.setShippingAddressId(entity.getShippingAddressId());
	        dto.setCarrier(Carrier.valueOf(entity.getCarrier().toString()));
	        dto.setTrackingNumber(entity.getTrackingNumber());
	        dto.setArea(Area.valueOf(entity.getArea().name()) != null ? Area.valueOf(entity.getArea().toString()) : null);
	        dto.setShipmentStatus(Status.valueOf(entity.getShipmentStatus().name()) != null ? Status.valueOf(entity.getShipmentStatus().toString())  : null);
	        dto.setEstimatedDeliveryDate(entity.getEstimatedDeliveryDate());
	        dto.setShippedAt(entity.getShippedAt());
	        dto.setDeliveredAt(entity.getDeliveredAt());

	        return dto;
	    }

	    public ShippingOrder toEntity(ShippingOrderDto dto) {
	        if (dto == null) return null;

	        ShippingOrder entity = new ShippingOrder();
	        entity.setShipmentId(dto.getShipmentId());
	        entity.setOrderId(dto.getOrderId());
	        entity.setShippingAddressId(dto.getShippingAddressId());
	        entity.setCarrier(dto.getCarrier() != null ? ShippingOrder.Carrier.valueOf(dto.getCarrier().toString()) : null);
	        entity.setTrackingNumber(dto.getTrackingNumber());
	        entity.setArea(dto.getArea() != null ? ShippingOrder.Area.valueOf(dto.getArea().toString()) : null);
	        entity.setShipmentStatus(dto.getShipmentStatus() != null ? ShippingOrder.Status.valueOf(dto.getShipmentStatus().toString()) : null);
	        entity.setEstimatedDeliveryDate(dto.getEstimatedDeliveryDate());
	        entity.setShippedAt(dto.getShippedAt());
	        entity.setDeliveredAt(dto.getDeliveredAt());

	        return entity;
	    }
	}

