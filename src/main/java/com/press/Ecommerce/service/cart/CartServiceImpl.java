package com.press.Ecommerce.service.cart;

import java.math.BigDecimal;
import java.util.Optional;
//import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.press.Ecommerce.exception.ResourceNotFoundException;
import com.press.Ecommerce.modal.Cart;
//import com.press.Ecommerce.modal.CartItem;
import com.press.Ecommerce.repository.CartItemRepository;
import com.press.Ecommerce.repository.CartRepository;


@Service
public class CartServiceImpl implements CartService {

	 @Autowired
	    private CartRepository cartRepository;

	    @Autowired
	    private CartItemRepository cartItemRepository;
	    
	    @Autowired
	    private AtomicLong cartIdGenerator = new AtomicLong(0);

	    @Override
	    public Cart getCart(Long id) {
	         Cart cart =cartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cart not Found!!!"));
	         
	         BigDecimal totalAmount =cart.updateTotalAmount();
	         cart.setTotalAmount(totalAmount);
	         
	         return cartRepository.save(cart);
 	         
	    }
	        

	    @Override
	    public void clearCart(Long cartId) {
	    	 // Retrieve the cart by its ID
	        Optional<Cart> cartOptional = cartRepository.findById(cartId);
	        if (!cartOptional.isPresent()) {
	            throw new RuntimeException("Cart not found!");
	        }
	        Cart cart = cartOptional.get();

	        // Clear all items from the cart
	        cart.getItems().clear();

	        // Save the updated cart
	        cartRepository.save(cart);
	    }

	    @Override
	    public BigDecimal getTotalPrice(Long id) {
	        Cart cart = getCart(id);  // Get the cart using the above method
	        

	        return cart.getTotalAmount();
	    }
	
	@Override
	public Long initilizerNewCart() {
		
		Cart newCart = new  Cart();
		
		Long newCartId = cartIdGenerator.incrementAndGet();
		newCart.setId(newCartId);
		return cartRepository.save(newCart).getId();
				
	}


	@Override
	public Cart getCartByUserId(Long id) {
		
		if(id== null) {
			new ResourceNotFoundException("Id not found");
		}
		
		
		return cartRepository.getById(id);
		
	
	}

}
