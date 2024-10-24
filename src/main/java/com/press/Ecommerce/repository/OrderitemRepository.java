package com.press.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.press.Ecommerce.modal.OrderItem;

public interface OrderitemRepository extends JpaRepository<OrderItem, Long> {

}
