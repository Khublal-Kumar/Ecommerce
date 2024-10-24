package com.press.Ecommerce.modal;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.press.Ecommerce.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<OrderItem> orderItem= new HashSet<OrderItem>();
    
    
    @ManyToOne
    @JoinColumn(name = "myUser_id")
    private MyUser myUser;
    
    

    // Constructor to initialize with default values
    public Order(BigDecimal totalAmount, OrderStatus status) {
        this.date = LocalDateTime.now();  // Set current timestamp
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Method to update the total amount
    public void updateTotalAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.totalAmount = amount;
    }

    // Method to complete the order
    public void completeOrder() {
        if (this.status != OrderStatus.CANCELED) {
            this.status = OrderStatus.COMPLETED;
        } else {
            throw new IllegalStateException("Cannot complete a canceled order");
        }
    }

    // Method to cancel the order
    public void cancelOrder() {
        if (this.status == OrderStatus.PENDING) {
            this.status = OrderStatus.CANCELED;
        } else {
            throw new IllegalStateException("Order cannot be canceled once it is completed");
        }
    }

    // Method to get the order date as a formatted string
    public String getFormattedDate() {
        return date.toString(); // You can format this further based on your requirements
    }

    // Additional method to check if the order is completed
    public boolean isCompleted() {
        return this.status == OrderStatus.COMPLETED;
    }

    // Additional method to check if the order is pending
    public boolean isPending() {
        return this.status == OrderStatus.PENDING;
    }

    // Additional method to check if the order is canceled
    public boolean isCanceled() {
        return this.status == OrderStatus.CANCELED;
    }
}