package com.press.Ecommerce.modal;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    // Constructor to initialize quantity, unitPrice, and product
    public OrderItem(int quantity, BigDecimal unitPrice, Product product) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        setTotalPrice();
    }

    // Method to calculate and set total price for the order item
    public void setTotalPrice() {
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) < 0 || quantity <= 0) {
            throw new IllegalArgumentException("Invalid unit price or quantity");
        }
        this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
    }

    // Method to update the quantity of an order item
    public void updateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
        setTotalPrice(); // Update total price based on the new quantity
    }

    // Method to update the unit price of an order item
    public void updateUnitPrice(BigDecimal unitPrice) {
        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative");
        }
        this.unitPrice = unitPrice;
        setTotalPrice(); // Update total price based on the new unit price
    }

    // Method to get a formatted string representation of the total price
    public String getFormattedTotalPrice() {
        return totalPrice.toString(); // You can customize the format as needed
    }

	public OrderItem(int quantity, BigDecimal unitPrice, Product product, Order order) {
		super();
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.product = product;
		this.order = order;
		 setTotalPrice();
	}
}