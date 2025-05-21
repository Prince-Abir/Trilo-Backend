package cart_service.controllers;

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

import cart_service.dto.AddToCartRequest;
import cart_service.entities.Cart;
import cart_service.services.CartService;


@RestController
@RequestMapping("/trilo/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	@PostMapping
	public Cart addtoCart(@RequestBody AddToCartRequest cart) {

		return cartService.addToCart(cart);

	}

	@GetMapping("/{cartId}")
	public Cart getCart(@PathVariable long cartId) {

		return cartService.getCart(cartId);
	}
	
	@GetMapping("/user/{userId}")
	public List<Cart> getCartsByUserId(@PathVariable long userId) {

		return cartService.getCartByUserId(userId);
	}

	@GetMapping("/carts")
	public List<Cart> getAllCarts() {

		return cartService.getAllCarts();
	}

	@PutMapping("/{cartId}")
	public Cart updateCart(@RequestBody Cart newCart,@PathVariable long cartId) {
		
		return cartService.updateCart(newCart, cartId);
	}

	
	@DeleteMapping("/{cartId}")
	public boolean deleteCart(long cartId) {
		return cartService.deleteCart(cartId);

	}


}
