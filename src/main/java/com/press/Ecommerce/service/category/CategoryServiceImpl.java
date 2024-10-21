 package com.press.Ecommerce.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.press.Ecommerce.exception.AlreadyExistException;
import com.press.Ecommerce.exception.CategoryNotFoundException;
import com.press.Ecommerce.modal.Category;
import com.press.Ecommerce.repository.CategoryRepository;



@Service
public class CategoryServiceImpl implements CategoryService {
	
	
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("category Not Found!!!"));
	}

	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByName(name);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return Optional.of(category).filter(c->!categoryRepository.existsByName(category.getName()))
				.map(categoryRepository::save)
				.orElseThrow(()->new AlreadyExistException(category.getName() +"Already Exist"));
				
	}

	@Override
	public Category updateCategory(Category category, Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(getCategoryById(id)).map(oldCategory->{
			oldCategory.setName(category.getName());
			return categoryRepository.save(oldCategory);
			}).orElseThrow(()-> new CategoryNotFoundException("category Not Found!!!"));
				
	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		
		categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, ()->{
			
			throw new CategoryNotFoundException("category Not Found!!!");
		});   
		
	}

}
