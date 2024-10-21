package com.press.Ecommerce.service.product;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.press.Ecommerce.exception.ProductNotFoundExceptions;
import com.press.Ecommerce.modal.Category;
import com.press.Ecommerce.modal.Product;
import com.press.Ecommerce.productPayload.AddProductRequest;
import com.press.Ecommerce.productPayload.ProductUpdateRequest;
import com.press.Ecommerce.repository.CategoryRepository;
import com.press.Ecommerce.repository.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService {
	
	
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	

	@Override
	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(()->new ProductNotFoundExceptions("Hey Buddy Product is Not There!!!!"));
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		
		productRepository.findById(id).ifPresentOrElse(productRepository::delete,
				()->{throw new ProductNotFoundExceptions("Hey Buddy Product is Not There!!!!");
					});
		
	}

	
	
	@Override
	public Product updateProduct(ProductUpdateRequest product, Long productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId)
				.map(existing-> updateExistingProduct(existing, product))
				.map(productRepository::save)
				.orElseThrow(()->new ProductNotFoundExceptions("Hey Buddy Product is Not There!!!!"))
				
				
				;
	} 
	
	
	
	
	private Product updateExistingProduct(Product existingProduct,ProductUpdateRequest request) {
		
		
		existingProduct.setBrand(request.getBrand());
		
		Category byName = categoryRepository.findByName(request.getCategory().getName());
		
		existingProduct.setCategory(byName );
		existingProduct.setDescription(request.getDescription());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setName(request.getName());
		existingProduct.setPrice(request.getPrice());
		
		return existingProduct;
				
	}
	
	
	
	

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryName(category);
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		// TODO Auto-generated method stub
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
		// TODO Auto-generated method stub
		return productRepository.findByCategoryNameAndBrand(category,brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return productRepository.findByBrandAndName(brand,name);
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		// TODO Auto-generated method stub
		return productRepository.countByBrandAndName(brand,name);
	}

	@Override
	public Product addProduct(AddProductRequest request) {
		// TODO Auto-generated method stub
		
		
		
		Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName())).orElseGet(()->{
			
			
			Category newCategory = new Category(request.getCategory().getName());
				return categoryRepository.save(newCategory);
		
		});
		
		request.setCategory(category);
		return productRepository.save(createProduct(request, category));
	}

	private Product createProduct(AddProductRequest request,Category category) {
		
		return new Product(
				request.getName(), 
				request.getBrand(), 
				request.getDescription(),
				request.getPrice(), 
				request.getInventory(),
				category);
	}

	
	
	

	

}
