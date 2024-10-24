package com.press.Ecommerce.service.order;

import java.util.List;
import java.util.Optional;

import com.press.Ecommerce.modal.Order;

public interface OrderService {
	
	Order placeOrder(Long id);
	Order getOrder(Long id);
	List<Order> getMyUserOrders(Long myuserId);

}
