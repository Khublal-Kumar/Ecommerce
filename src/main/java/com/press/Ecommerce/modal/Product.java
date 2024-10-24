package com.press.Ecommerce.modal;

import java.math.BigDecimal;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter

@NoArgsConstructor
@ToString
@Entity
public class Product {
	
	


	public Product(String name, String brand, String description, BigDecimal price, int inventory, Category category) {
		super();
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.category = category;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 private String name;
	 
	 private String brand;
	 
	 private String description;
	 
	 private BigDecimal price;
	 
	 @JsonIgnore
	 private int inventory;
	 
	 
	 @ManyToOne
	 @JoinColumn(name = "category_id")
	 private Category category;
	 
	 
	 @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL,orphanRemoval = true)
	 private List<Image> images; 

}
