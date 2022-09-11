package com.foodbox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.foodbox.exception.ResourceNotFoundException;
import com.foodbox.model.Category;
import com.foodbox.repository.CategoryRepository;
import com.foodbox.service.CategoryService;


@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService catService;

	@Autowired
	private CategoryRepository catRepo;

	@Secured("ROLE_ADMIN")
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category categories = catService.addCategory(category);
		if (categories != null) {
			return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Category>(category, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/")
	public List<Category> getAllCategories() {
		return catService.findAllCategories();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryBYId(@PathVariable Long id) {
		Category category = catRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
		return ResponseEntity.ok(category);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category newCategory) {
		Category category = catRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
		category.setCategoryName(newCategory.getCategoryName());
		Category updateCategory = catRepo.save(category);
		return ResponseEntity.ok(updateCategory);

	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCategoryById(@PathVariable Long id) {
		Category category = catRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not exist with id: " + id));
		catRepo.delete(category);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
