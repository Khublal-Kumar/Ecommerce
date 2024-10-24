package com.press.Ecommerce.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.press.Ecommerce.modal.Cart;
import com.press.Ecommerce.service.cart.CartService;
import com.press.Ecommerce.Response.APIResponse;
import com.press.Ecommerce.exception.ResourceNotFoundException;

@RestController
@RequestMapping("${api.prefix}/carts")
public class CartController {

	
	
	 @Autowired
	    private CartService cartService;

	    // Get Cart by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<APIResponse> getCart(@PathVariable Long id) {
	        try {
	            Cart cart = cartService.getCart(id);
	            APIResponse response = new APIResponse("Cart fetched successfully",  cart);
	            return ResponseEntity.ok(response);
	        } catch (ResourceNotFoundException e) {
	            APIResponse response = new APIResponse("Cart not found: " + e.getMessage(), HttpStatus.NOT_FOUND.value());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        } catch (Exception e) {
	            APIResponse response = new APIResponse("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }

	    // Clear Cart by ID
	    @DeleteMapping("/clear/{id}")
	    public ResponseEntity<APIResponse> clearCart(@PathVariable Long id) {
	        try {
	            cartService.clearCart(id);
	            APIResponse response = new APIResponse("Cart cleared successfully", HttpStatus.OK.value());
	            return ResponseEntity.ok(response);
	        } catch (ResourceNotFoundException e) {
	            APIResponse response = new APIResponse("Cart not found: " + e.getMessage(), HttpStatus.NOT_FOUND.value());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        } catch (Exception e) {
	            APIResponse response = new APIResponse("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }

	    // Get Total Price of Cart by ID
	    @GetMapping("/total-price/{id}")
	    public ResponseEntity<APIResponse> getTotalPrice(@PathVariable Long id) {
	        try {
	            BigDecimal totalPrice = cartService.getTotalPrice(id);
	            APIResponse response = new APIResponse("Total price fetched successfully",  totalPrice);
	            return ResponseEntity.ok(response);
	        } catch (ResourceNotFoundException e) {
	            APIResponse response = new APIResponse("Cart not found: " + e.getMessage(), HttpStatus.NOT_FOUND.value());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        } catch (Exception e) {
	            APIResponse response = new APIResponse("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	        }
	    }
	
	 
}
