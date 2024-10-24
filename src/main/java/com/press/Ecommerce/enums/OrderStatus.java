package com.press.Ecommerce.enums;

public enum OrderStatus {
	
	PENDING,     // Order has been created but not yet completed
    COMPLETED,   // Order has been completed and processed
    CANCELED,    // Order has been canceled by the user or system
    PROCESSING,  // Order is being processed (e.g., packed, shipped)
    SHIPPED,     // Order has been shipped to the customer
    DELIVERED,   // Order has been delivered to the customer
    RETURNED,    // Order has been returned by the customer
    REFUNDED;    // Order has been refunded to the customer
}
