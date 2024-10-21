package com.press.Ecommerce.service.product;

import java.util.List;

import com.press.Ecommerce.modal.Product;
import com.press.Ecommerce.productPayload.AddProductRequest;
import com.press.Ecommerce.productPayload.ProductUpdateRequest;

public interface ProductService {
	
	
	
	Product addProduct (AddProductRequest product); 

	

	Product getProductById(Long id); 

	

	void deleteProductById(Long id); 

	

	Product updateProduct (ProductUpdateRequest product, Long productId);

	
	List<Product> getAllProducts(); 

	

	List<Product> getProductsByCategory (String category);
	
	
	List<Product> getProductsByBrand (String brand); 

	

	List<Product> getProductsByCategoryAndBrand(String category, String brand); 

	

	List<Product> getProductsByName(String name); 

	

	List<Product> getProductsByBrandAndName (String category, String name);

	

	Long countProductsByBrandAndName (String brand, String name); 


}
