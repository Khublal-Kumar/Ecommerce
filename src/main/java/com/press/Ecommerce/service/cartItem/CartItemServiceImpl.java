
package com.press.Ecommerce.service.cartItem;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.press.Ecommerce.exception.CartNotFoundException;
import com.press.Ecommerce.exception.ProductNotFoundException;
import com.press.Ecommerce.exception.ResourceNotFoundException;
import com.press.Ecommerce.modal.Cart;
import com.press.Ecommerce.modal.CartItem;
import com.press.Ecommerce.modal.Product;
import com.press.Ecommerce.repository.CartItemRepository;
import com.press.Ecommerce.repository.CartRepository;
import com.press.Ecommerce.repository.ProductRepository;
import com.press.Ecommerce.service.cart.CartService;

@Service
public class CartItemServiceImpl implements CartItemService {
	
		@Autowired
	    private CartRepository cartRepository;

	    @Autowired
	    private ProductRepository productRepository;

	    @Autowired
	    private CartItemRepository cartItemRepository;
	    
	    @Autowired
	    private CartService  cartService;

	    @Override
	    public CartItem addItemToCart(Long cartId, Long productId, int quantity) {

	    	
	    	 // 1. Get the cart
	        Cart cart = cartRepository.findById(cartId)
	                .orElseThrow(() -> new CartNotFoundException("Cart not found with ID: " + cartId));

	        // 2. Get the product
	        Product product = productRepository.findById(productId)
	                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

	        // 3. Check if the product is already in the cart
	        Optional<CartItem> existingCartItem = cartItemRepository.findByCartAndProduct(cart, product);

	        if (existingCartItem.isPresent()) {
	            // 4. If Yes, then increase the quantity with the requested quantity
	            CartItem cartItem = existingCartItem.get();
	            cartItem.setQuantity(cartItem.getQuantity() + quantity);
	            cartItem.setTotalPrice(); // Update the total price
	            return cartItemRepository.save(cartItem);
	        } else {
	            // 5. If No, then initiate a new CartItem entry
	            CartItem newCartItem = new CartItem();
	            newCartItem.setCart(cart);
	            newCartItem.setProduct(product);
	            newCartItem.setQuantity(quantity);
	            newCartItem.setUnitPrice(product.getPrice());
	            newCartItem.setTotalPrice(); // Set the total price

	            return cartItemRepository.save(newCartItem);
	        }
	    }

	    @Override
	    public void removeItemFromCart(Long cartId, Long productId) {
	    	 // 1. Get the cart
	        Cart cart = cartService.getCart(cartId);
	        CartItem itemToRemove = cart.getItems()
	        .stream()
	        .filter(i->i.getProduct().getId().equals(productId))
	        .findFirst().orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
	        
	        cart.removeItem(itemToRemove);
	        cartRepository.save(cart);

	        
	    }

	    @Override
	    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
	        // 1. Get the cart
	        Cart cart = cartService.getCart(cartId);

	        // 2. Find the cart item by productId
	        cart.getItems() // Set<CartItem>
	            .stream()  // Stream<CartItem>
	            .filter(item -> item.getProduct().getId().equals(productId))  // Filter by productId
	            .findFirst()  // Find the first matching cart item
	            .ifPresent(item -> {  // If present, update the cart item
	                // 3. Update the quantity
	                item.setQuantity(quantity);

	                // 4. Update the unit price
	                item.setUnitPrice(item.getProduct().getPrice());

	                // 5. Update the total price
	                item.setTotalPrice();
	            });

	        // 6. Calculate the total amount for the cart
	        BigDecimal totalAmount = cart.getTotalAmount();
	        cart.setTotalAmount(totalAmount);

	        // 7. Save the updated cart
	        cartRepository.save(cart);
	    }
	    
	    @Override
	    public CartItem getCartItem(Long cartId, Long productId) {
	    	
	    	Cart cart = cartService.getCart(cartId) ;// Set<CartItem>
		    return cart.getItems()
		    		.stream()  // Stream<CartItem>
		            .filter(item -> item.getProduct().getId().equals(productId))  // Filter by productId
		            .findFirst()  // Find the first matching cart item
		            .orElseThrow(() -> new ResourceNotFoundException("Product not found"));  // Throw exception if not found

		      
	    	
	    }

}
