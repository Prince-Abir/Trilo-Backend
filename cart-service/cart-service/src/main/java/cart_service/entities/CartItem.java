package cart_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart_items")
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartItemId;

	//private long cartId; // From Cart Class

	private long productVariantId; // From ProductVariant Class

	private String itemName;

	private String itemImageUrl;

	private String size;

	private String color;

	private int quantity;

	private double price;

	private double subTotal; // quantity * price

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	@JsonBackReference
	private Cart cart;

}
