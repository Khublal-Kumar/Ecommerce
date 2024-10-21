package com.press.Ecommerce.modal;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CartItem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	
	private int quantity;
	
	private BigDecimal unitPrice;
	
	private BigDecimal totalPrice;
	
	
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private Cart cart;
	

	
	 public  void setTotalPrice() {
	        if (this.unitPrice == null || this.unitPrice.compareTo(BigDecimal.ZERO) < 0 || this.quantity < 0) {
	            throw new IllegalArgumentException("Invalid unit price or quantity");
	        }
	       this.totalPrice = this.unitPrice.multiply(new BigDecimal(this.quantity));
	    }
	
	
	
	
}
