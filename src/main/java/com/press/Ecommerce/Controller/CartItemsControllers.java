package com.press.Ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.press.Ecommerce.Response.APIResponse;
import com.press.Ecommerce.exception.ResourceNotFoundException;
import com.press.Ecommerce.modal.CartItem;
import com.press.Ecommerce.service.cart.CartService;
import com.press.Ecommerce.service.cartItem.CartItemService;

@RestController
@RequestMapping("${api.prefix}/cartitems")
public class CartItemsControllers {
	 @Autowired
	    private CartItemService cartItemService;
	 
	 @Autowired
	 private CartService cartService;

	    // 1. Add a cart item to the cart
	    @PostMapping("/add")
	    public ResponseEntity<APIResponse> addItemToCart(
	            @RequestParam(required = false) Long cartId,
	            @RequestParam Long productId,
	            @RequestParam int quantity) {
	    	
	    	if(cartId ==null) {
	    		cartId=	cartService.initilizerNewCart(); 
	    	}

	        CartItem cartItem = cartItemService.addItemToCart(cartId, productId, quantity);
	        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse("Item added to cart successfully", cartItem));
	    }

	    // 2. Remove an item from the cart
	    @DeleteMapping("/remove")
	    public ResponseEntity<APIResponse> removeItemFromCart(
	            @RequestParam Long cartId,
	            @RequestParam Long productId) {

	        try {
				cartItemService.removeItemFromCart(cartId, productId);
				return ResponseEntity.ok(new APIResponse("Item removed from cart successfully", null));
			} catch (ResourceNotFoundException e) {
				// TODO Auto-generated catch block
				 return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(e.getMessage(), null));
			}
	    }

	    // 3. Update quantity of an item in the cart
	    @PutMapping("/update-quantity")
	    public ResponseEntity<APIResponse> updateItemQuantity(
	            @RequestParam Long cartId,
	            @RequestParam Long productId,
	            @RequestParam int quantity) {

	        cartItemService.updateItemQuantity(cartId, productId, quantity);
	        return ResponseEntity.ok(new APIResponse("Item quantity updated successfully", null));
	    }

	    // 4. Get a specific cart item by cartId and productId
	    @GetMapping("/get")
	    public ResponseEntity<APIResponse> getCartItem(
	            @RequestParam Long cartId,
	            @RequestParam Long productId) {

	        CartItem cartItem = cartItemService.getCartItem(cartId, productId);
	        return ResponseEntity.ok(new APIResponse("Cart item found", cartItem));
	    }

}
