package com.press.Ecommerce.productPayload;

import java.math.BigDecimal;

import com.press.Ecommerce.modal.Category;

import lombok.Data;



@Data
public class ProductUpdateRequest {

	
	
	private String name;
	 
	 private String brand;
	 
	 private String description;
	 
	 private BigDecimal price;
	 
	 private int inventory;
	 
	 
	 
	 private Category category;
}
