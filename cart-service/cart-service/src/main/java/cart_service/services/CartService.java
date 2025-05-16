package cart_service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cart_service.dto.AddToCartRequest;
import cart_service.dto.ProductVariantDto;
import cart_service.entities.Cart;
import cart_service.entities.CartItem;
import cart_service.feingClients.ProductVariantClient;
import cart_service.repositories.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductVariantClient productVariantClient;

	private double totalAmount;

	public Cart addtoCart(AddToCartRequest cartItem) {

		Cart cart = cartRepository.findByUserId(cartItem.getUserId());

		if (cart == null) {

			cart = new Cart();
			cart.setUserId(cartItem.getUserId());

		}

		ProductVariantDto variant = productVariantClient.getProductVariant(cartItem.getProductVariantId());

		if (cartItem.getQuantity() > variant.getQuantity()) {

			throw new RuntimeException("Sorry, this product not avaible! " + cartItem.getItemName());

		}

		Optional<CartItem> existingItem = cart.getCartItems().stream()
				.filter(i -> i.getProductVariantId() == cartItem.getProductVariantId()).findFirst();
		if (existingItem.isPresent()) {
			
			 CartItem item = existingItem.get();

			int newQuantity = cartItem.getQuantity() + item.getQuantity();

			if (newQuantity > variant.getQuantity()) {
				throw new RuntimeException("Sorry, this product not avaible! " + cartItem.getItemName());
			}

			double subTotal = newQuantity * cartItem.getPrice();
			item.setQuantity(newQuantity);
			item.setSubTotal(subTotal);

		} else {


			CartItem newItem = new CartItem();

			newItem.setColor(cartItem.getColor());
			newItem.setItemName(cartItem.getItemName());
			newItem.setItemImageUrl(cartItem.getItemImageUrl());
			newItem.setQuantity(cartItem.getQuantity());
			newItem.setPrice(cartItem.getPrice());
			newItem.setProductVariantId(cartItem.getProductVariantId());
			newItem.setSize(cartItem.getSize());
			newItem.setSubTotal(cartItem.getPrice() * cartItem.getQuantity());
			newItem.setCart(cart);

			cart.getCartItems().add(newItem);

		}

		double toatalAmount = cart.getCartItems().stream().mapToDouble(CartItem::getSubTotal).sum();
		cart.setTotalAmount(toatalAmount);

		return cartRepository.save(cart);

	}
	
	

	public Cart getCart(long cartId) {

		return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found!!"));
	}

	public List<Cart> getAllCarts() {

		return cartRepository.findAll();
	}

	public Cart updateCart(Cart newCart, long cartId) {

		Cart dbCart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found!!"));

		newCart.setCart_id(dbCart.getCart_id());

		newCart.getCartItems().forEach(item -> {

			double total = item.getPrice() * item.getQuantity();
			item.setSubTotal(total);

			totalAmount += total;

		});

		newCart.setTotalAmount(totalAmount);
		totalAmount = 0;

		return cartRepository.save(newCart);

	}

	public boolean deleteCart(long cartId) {

		Cart dbCart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found!!"));

		cartRepository.delete(dbCart);
		return true;

	}

}