package com.press.Ecommerce.service.cart;

import java.math.BigDecimal;
//import java.util.Optional;

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

	    @Override
	    public Cart getCart(Long id) {
	         Cart cart =cartRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cart not Found!!!"));
	         
	         BigDecimal totalAmount =cart.getTotalAmount();
	         cart.setTotalAmount(totalAmount);
	         
	         return cartRepository.save(cart);
 	         
	    }
	        

	    @Override
	    public void clearCart(Long id) {
	        Cart cart = getCart(id);  // Get cart using the above method
	        cart.getItems().clear();  // Clear all the items from the cart
	        cartRepository.save(cart);  // Save the cart after clearing items
	    }

	    @Override
	    public BigDecimal getTotalPrice(Long id) {
	        Cart cart = getCart(id);  // Get the cart using the above method
	        

	        return cart.getTotalAmount();
	    }
	
	
	

}
