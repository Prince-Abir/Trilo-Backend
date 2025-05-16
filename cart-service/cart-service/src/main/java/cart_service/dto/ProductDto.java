package cart_service.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

	private long productId;

	private String productName;

	private String productDescription;

	private String productCategory;

	private double productRating;

	private boolean isOnSale;

	private List<ProductVariantDto> productVariantDto = new ArrayList<>();

}
