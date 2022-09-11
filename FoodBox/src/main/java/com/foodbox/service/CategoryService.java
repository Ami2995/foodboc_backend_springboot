package com.foodbox.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.model.Category;
import com.foodbox.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category addCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	public List<Category> findAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Optional<Category> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}
}
