package com.press.Ecommerce.service.cart;

import java.math.BigDecimal;
import java.util.List;

import com.press.Ecommerce.modal.Cart;
import com.press.Ecommerce.modal.CartItem;

public interface CartService {
	
	   Cart getCart(Long id);
	  
	   void clearCart(Long id);
	  
	   BigDecimal getTotalPrice(Long id);
	
    

}
