package com.press.Ecommerce.service.cartItem;

import com.press.Ecommerce.modal.CartItem;

public interface CartItemService {
	
	 // Add a cart item to the cart
    CartItem addItemToCart(Long cartId, Long productId, int quantity);

    // Remove a cart item from the cart
    void removeItemFromCart(Long cartId, Long productId);

    // Update the quantity of a cart item
    void updateItemQuantity(Long cartId, Long productId, int quantity);

	CartItem getCartItem(Long cartId, Long productId);

}
