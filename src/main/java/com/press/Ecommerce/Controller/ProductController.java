package com.press.Ecommerce.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.press.Ecommerce.Response.APIResponse;
import com.press.Ecommerce.exception.ProductNotFoundExceptions;
import com.press.Ecommerce.modal.Product;
import com.press.Ecommerce.productPayload.AddProductRequest;
import com.press.Ecommerce.productPayload.ProductUpdateRequest;
import com.press.Ecommerce.service.product.ProductService;

@RestController
@RequestMapping("${api.prefix}/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	// 2. Get All Product
	@GetMapping("/all")
	public ResponseEntity<APIResponse> getAllProduct(){
		
		List<Product> allProducts = productService.getAllProducts();
		
		return ResponseEntity.ok(new APIResponse("Success!!!", allProducts));
	}
	//Get products by Id
	@GetMapping("/product/{id}")
	public ResponseEntity<APIResponse> getProductById(@PathVariable Long id){
		
		try {
			Product productById = productService.getProductById(id);
			return ResponseEntity.ok(new APIResponse("Found!!!", productById));
		} catch (ProductNotFoundExceptions e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(e.getMessage(), " ")); 
		}
	}
	// 2. Get products by category name
    @GetMapping("/category/{category}")
    public ResponseEntity<APIResponse> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return ResponseEntity.ok(new APIResponse("Found", products));
    }

    // 3. Get products by brand name
    @GetMapping("/brand/{brand}")
    public ResponseEntity<APIResponse> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = productService.getProductsByBrand(brand);
        return ResponseEntity.ok(new APIResponse("Found", products));
    }

    // 4. Get products by category and brand
    @GetMapping("/category/{category}/brand/{brand}")
    public ResponseEntity<APIResponse> getProductsByCategoryAndBrand(
            @PathVariable String category,
            @PathVariable String brand) {
        List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
        return ResponseEntity.ok(new APIResponse("Found", products));
    }

    // 5. Get products by name
    @GetMapping("/name/{name}")
    public ResponseEntity<APIResponse> getProductsByName(@PathVariable String name) {
        List<Product> products = productService.getProductsByName(name);
        return ResponseEntity.ok(new APIResponse("Found", products));
    }

    // 6. Get products by brand and name
    @GetMapping("/brand/{brand}/name/{name}")
    public ResponseEntity<APIResponse> getProductsByBrandAndName(
            @PathVariable String brand,
            @PathVariable String name) {
        List<Product> products = productService.getProductsByBrandAndName(brand, name);
        return ResponseEntity.ok(new APIResponse("Found", products));
    }

    // 7. Count products by brand and name
    @GetMapping("/count/brand/{brand}/name/{name}")
    public ResponseEntity<Long> countProductsByBrandAndName(
            @PathVariable String brand,
            @PathVariable String name) {
        Long count = productService.countProductsByBrandAndName(brand, name);
        return ResponseEntity.ok(count);
    }
    
    //8. Add product 

    @PostMapping("/add")
    public ResponseEntity<APIResponse> addProduct(@RequestBody AddProductRequest request) {
        try {
			Product newProduct = productService.addProduct(request);
			return ResponseEntity.ok(new APIResponse("Added Succesfully", newProduct));
		} catch (ProductNotFoundExceptions e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Failed to add product", " "));
		}
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long id) {
        try {
			Product updatedProduct = productService.updateProduct(request, id);
			return ResponseEntity.ok(new APIResponse("Update Succesfully", updatedProduct));
		} catch (ProductNotFoundExceptions e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse("Failed to update product", " "));
		}
    }

}
