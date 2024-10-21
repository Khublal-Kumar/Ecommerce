package com.press.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.press.Ecommerce.modal.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
