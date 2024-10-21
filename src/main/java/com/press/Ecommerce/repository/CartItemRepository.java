package com.press.Ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.press.Ecommerce.modal.Cart;
import com.press.Ecommerce.modal.CartItem;
import com.press.Ecommerce.modal.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	Optional<CartItem> findByCartAndProduct(Cart cart, Product product);

}
