package com.press.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.press.Ecommerce.Response.APIResponse;
import com.press.Ecommerce.exception.AlreadyExistException;
import com.press.Ecommerce.exception.CategoryNotFoundException;
import com.press.Ecommerce.modal.Category;
import com.press.Ecommerce.service.category.CategoryService;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/all")
	public ResponseEntity<APIResponse> getAllCategories(){
		
		try {
			List<Category> allCategories = categoryService.getAllCategories();
			
			return ResponseEntity.ok(new APIResponse("Found!!!", allCategories));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR));
		}
		
		
	}
	
	@PostMapping("/add")
	public ResponseEntity<APIResponse> addCategories(@RequestBody Category name){
		
		
		try {
			Category theCategory = categoryService.addCategory(name);
			
			return ResponseEntity.ok(new APIResponse("Success", theCategory));
		} catch (AlreadyExistException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new APIResponse(e.getMessage(), " "));
		}
	}
	
	@GetMapping("/category/{id}/category")
	public ResponseEntity<APIResponse> getCategoryById(@PathVariable Long id){
		
		try {
			Category TheCategoryById = categoryService.getCategoryById(id);
			return ResponseEntity.ok(new APIResponse("Found", TheCategoryById));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), " "));
		}
		
	}
	
	@GetMapping("/{name}/category")
	public ResponseEntity<APIResponse> getCategoryByName(@PathVariable String name){
		
		try {
			Category TheCategoryByName = categoryService.getCategoryByName(name);
			return ResponseEntity.ok(new APIResponse("Found", TheCategoryByName));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), " "));
		}
		
	}
	
	 
	@DeleteMapping("/category/{id}/delete")
	public ResponseEntity<APIResponse> deleteCategoryById(@PathVariable Long id){
		
		try {
			 categoryService.deleteCategoryById(id);
			return ResponseEntity.ok(new APIResponse("Deleted Succesfully!!! ", " "));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), " "));
		}
		
	}
	@PutMapping("/category/{id}/update")
	public ResponseEntity<APIResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
		
		try {
			 Category updateCategory = categoryService.updateCategory(category, id);
			return ResponseEntity.ok(new APIResponse("update Succesfully", updateCategory));
		} catch (CategoryNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), " "));
		}
		
	}
	

}

/*package com.press.Ecommerce.Controller; // Package declaration for organizing the controller classes.

import java.util.List; // Importing the List interface to work with lists of categories.

import org.springframework.beans.factory.annotation.Autowired; // Importing the annotation for dependency injection.
import org.springframework.http.HttpStatus; // Importing HttpStatus for HTTP response statuses.
import org.springframework.http.ResponseEntity; // Importing ResponseEntity to represent HTTP responses.
import org.springframework.web.bind.annotation.*; // Importing annotations for creating RESTful APIs.

import com.press.Ecommerce.Response.APIResponse; // Importing the APIResponse class for standardized responses.
import com.press.Ecommerce.exception.AlreadyExistException; // Importing custom exception for existing categories.
import com.press.Ecommerce.exception.CategoryNotFoundException; // Importing custom exception for not found categories.
import com.press.Ecommerce.modal.Category; // Importing the Category model.
import com.press.Ecommerce.service.category.CategoryService; // Importing the service class for category operations.

@RestController // Annotation indicating that this class is a REST controller.
@RequestMapping("${api.prefix}/categories") // Mapping the base URL for all category-related endpoints.
public class CategoryController {

    @Autowired // Injecting the CategoryService to use its methods.
    private CategoryService categoryService;

    // GET endpoint to retrieve all categories
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCategories() {
        try {
            List<Category> allCategories = categoryService.getAllCategories(); // Fetching all categories from the service.
            return ResponseEntity.ok(new APIResponse("Found!!!", allCategories)); // Returning a successful response with the categories.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) // Handling any exceptions and returning an error response.
                    .body(new APIResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    // POST endpoint to add a new category
    @PostMapping("/add")
    public ResponseEntity<APIResponse> addCategories(@RequestBody Category name) {
        try {
            Category theCategory = categoryService.addCategory(name); // Adding the new category via the service.
            return ResponseEntity.ok(new APIResponse("Success", theCategory)); // Returning a successful response.
        } catch (AlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT) // Handling conflict if the category already exists.
                    .body(new APIResponse(e.getMessage(), " "));
        }
    }

    // GET endpoint to retrieve a category by its ID
    @GetMapping("/category/{id}/category")
    public ResponseEntity<APIResponse> getCategoryById(@PathVariable Long id) {
        try {
            Category TheCategoryById = categoryService.getCategoryById(id); // Fetching the category by ID.
            return ResponseEntity.ok(new APIResponse("Found", TheCategoryById)); // Returning the found category.
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Handling the case where the category is not found.
                    .body(new APIResponse(e.getMessage(), " "));
        }
    }

    // GET endpoint to retrieve a category by its name
    @GetMapping("/{name}/category")
    public ResponseEntity<APIResponse> getCategoryByName(@PathVariable String name) {
        try {
            Category TheCategoryByName = categoryService.getCategoryByName(name); // Fetching the category by name.
            return ResponseEntity.ok(new APIResponse("Found", TheCategoryByName)); // Returning the found category.
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Handling the case where the category is not found.
                    .body(new APIResponse(e.getMessage(), " "));
        }
    }

    // DELETE endpoint to remove a category by its ID
    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<APIResponse> deleteCategoryById(@PathVariable Long id) {
        try {
            categoryService.deleteCategoryById(id); // Deleting the category by ID.
            return ResponseEntity.ok(new APIResponse("Found", " ")); // Returning a success response.
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Handling the case where the category is not found.
                    .body(new APIResponse(e.getMessage(), " "));
        }
    }

    // PUT endpoint to update a category by its ID
    @PutMapping("/category/{id}/update")
    public ResponseEntity<APIResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        try {
            Category updateCategory = categoryService.updateCategory(category, id); // Updating the category.
            return ResponseEntity.ok(new APIResponse("Update Successfully", updateCategory)); // Returning the updated category.
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND) // Handling the case where the category is not found.
                    .body(new APIResponse(e.getMessage(), " "));
        }
    }
}
 */
