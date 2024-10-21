package com.press.Ecommerce.service.category;

import java.util.List;

import com.press.Ecommerce.modal.Category;

public interface CategoryService {
	
	Category getCategoryById(Long id);

	

	Category getCategoryByName(String name);

	

	List<Category> getAllCategories();

	

	Category addCategory(Category category);

	

	Category updateCategory (Category category, Long Id);

	

	void deleteCategoryById(Long id);
}
