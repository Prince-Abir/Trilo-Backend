package cart_service.services;

import java.util.ArrayList;
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

	public Cart addToCart(AddToCartRequest cartItem) {
		double totalAmount = 0.0;

		List<Cart> carts = cartRepository.findByUserId(cartItem.getUserId());
		ProductVariantDto variant = productVariantClient.getProductVariant(cartItem.getProductVariantId());

		cartItem.setPrice(variant.getPrice());
		
		if (cartItem.getQuantity() > variant.getQuantity()) {
			throw new RuntimeException("Sorry, this product is not available! " + cartItem.getItemName());
		}

		// Look for an ACTIVE cart
		for (Cart cart : carts) {
			if ("ACTIVE".equalsIgnoreCase(cart.getCartStatus())) {
				Optional<CartItem> existingItem = cart.getCartItems().stream()
						.filter(i -> i.getProductVariantId() == cartItem.getProductVariantId()).findFirst();

				if (existingItem.isPresent()) {
					// Update quantity and subtotal
					CartItem existingCartItem = existingItem.get();
					int newQuantity = cartItem.getQuantity() + existingCartItem.getQuantity();

					if (newQuantity > variant.getQuantity()) {
						throw new RuntimeException("Sorry, this product is not available! " + cartItem.getItemName());
					}

					double subTotal = newQuantity * variant.getPrice();
					existingCartItem.setQuantity(newQuantity);
					existingCartItem.setSubTotal(subTotal);
				} else {
					// Add new item
					CartItem item = new CartItem();
					item.setItemName(cartItem.getItemName());
					item.setColor(cartItem.getColor());
					item.setItemImageUrl(cartItem.getItemImageUrl());
					item.setPrice(variant.getPrice());
					item.setProductVariantId(cartItem.getProductVariantId());
					item.setQuantity(cartItem.getQuantity());
					item.setSize(cartItem.getSize());
					item.setSubTotal(variant.getPrice() * cartItem.getQuantity());
					item.setCart(cart);

					cart.getCartItems().add(item);
				}

				// Recalculate total
				totalAmount = cart.getCartItems().stream().mapToDouble(CartItem::getSubTotal).sum();
				cart.setTotalAmount(totalAmount);
				cart.setCartStatus("ACTIVE");

				return cartRepository.save(cart);
			}
		}

		// No active cart found – create new one
		Cart newCart = new Cart();
		newCart.setUserId(cartItem.getUserId());
		newCart.setCartStatus("ACTIVE");

		CartItem item = new CartItem();
		item.setItemName(cartItem.getItemName());
		item.setColor(cartItem.getColor());
		item.setItemImageUrl(cartItem.getItemImageUrl());
		item.setPrice(variant.getPrice());
		item.setProductVariantId(cartItem.getProductVariantId());
		item.setQuantity(cartItem.getQuantity());
		item.setSize(cartItem.getSize());
		item.setSubTotal(variant.getPrice() * cartItem.getQuantity());
		item.setCart(newCart);

		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(item);
		newCart.setCartItems(cartItems);
		newCart.setTotalAmount(variant.getPrice() * cartItem.getQuantity());

		return cartRepository.save(newCart);
	}

	public Cart getCart(long cartId) {

		return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found!!"));
	}

	public List<Cart> getAllCarts() {

		return cartRepository.findAll();
	}

	public Cart updateCart(Cart newCart, long cartId) {

		double totalAmount = 0;

		Cart dbCart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found!!"));

		newCart.setCart_id(dbCart.getCart_id());

		for (CartItem item : newCart.getCartItems()) {
			double total = item.getPrice() * item.getQuantity();
			item.setSubTotal(total);
			totalAmount += total;
		}

		newCart.setTotalAmount(totalAmount);
		totalAmount = 0;

		return cartRepository.save(newCart);

	}

	public boolean deleteCart(long cartId) {

		Cart dbCart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart Not Found!!"));

		cartRepository.delete(dbCart);
		return true;

	}

	public List<Cart> getCartByUserId(long userId) {

		return cartRepository.findByUserId(userId);

	}

}