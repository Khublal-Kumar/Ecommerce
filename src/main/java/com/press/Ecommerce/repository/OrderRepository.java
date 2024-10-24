package com.press.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.press.Ecommerce.modal.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	// Find all orders by MyUser ID
    List<Order> findByMyUserId(Long userId);

	

}
