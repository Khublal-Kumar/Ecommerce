package com.press.Ecommerce.service.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.press.Ecommerce.enums.OrderStatus;
import com.press.Ecommerce.exception.ResourceNotFoundException;
import com.press.Ecommerce.modal.Cart;
import com.press.Ecommerce.modal.Order;
import com.press.Ecommerce.modal.OrderItem;
import com.press.Ecommerce.modal.Product;
import com.press.Ecommerce.repository.OrderRepository;
import com.press.Ecommerce.repository.ProductRepository;
import com.press.Ecommerce.service.cart.CartService;


@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartService cartService;
	
	

	@Override
	public Order placeOrder(Long id) {
		
		
		 Cart cart = cartService.getCartByUserId(id);
		 
		 Order order = createOrder(cart);
		 
		 List<OrderItem> orderItems = createOrderItems(order,cart);
		 
		 order.setOrderItem(new HashSet<>(orderItems));
		 
		 order.setTotalAmount(calculateTotalAmount(orderItems));
		 
		Order savedOrder = orderRepository.save(order);
		
		cartService.clearCart(cart.getId());
		
		return savedOrder;
	}
	
	
	
	
	
	private  Order createOrder(Cart cart) {
		
		Order order = new Order();
		
		//set user ...
		 order.setMyUser(cart.getMyUser());
		
		order.setStatus(OrderStatus.PENDING);
		order.setDate(LocalDateTime.now());
		
		return order;
		
		
	}
	
	private List<OrderItem> createOrderItems(Order order, Cart cart){
		
		
		return cart.getItems()
				.stream()
				.map(cartItems->{
					Product product = cartItems.getProduct();
					product.setInventory(product.getInventory()-cartItems.getQuantity());
					productRepository.save(product);
					
					return new OrderItem(
							cartItems.getQuantity()
							,cartItems.getUnitPrice()
							,product
							,order);
					
				}).toList();
	}
	
	
	
	
	private BigDecimal calculateTotalAmount(List<OrderItem> orderItems) {
		
		return orderItems.stream()
				.map(i->i.getUnitPrice().multiply(new BigDecimal(i.getQuantity())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public Order getOrder(Long orderId) {
		// TODO Auto-generated method stub
		return orderRepository.findById(orderId).orElseThrow(()-> new  ResourceNotFoundException("Order Not Found!!!"));
	}





	@Override
	public List<Order> getMyUserOrders(Long myuserId) {
		
		return orderRepository.findByMyUserId(myuserId);
	}
	
	

}
