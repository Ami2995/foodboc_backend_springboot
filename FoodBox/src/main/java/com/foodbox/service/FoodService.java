package com.foodbox.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.foodbox.model.FoodItems;
import com.foodbox.repository.FoodRepository;
import com.foodbox.util.FileUtils;

@Service
public class FoodService {

	@Autowired
	private FoodRepository foodRepo;

	public FoodItems addFoodItems(FoodItems food) {
		return foodRepo.save(food);
	}

	public List<FoodItems> getAllFoodItems() {
		return foodRepo.findAll();
	}

	public FoodItems getFoodItemById(Long id) {
		if (foodRepo.findById(id).isPresent()) {
			return foodRepo.findById(id).get();
		} else {
			return null;
		}
	}

	public List<FoodItems> getFoodByCategoryId(Long id) {
		return foodRepo.findAllByCategoryId(id);
	}
	
	public InputStream downloadImage(String fileName) throws FileNotFoundException {
		String path = FileUtils.folderPath;
		return new FileInputStream(path + File.separator + fileName);
	}
	
	public FoodItems updateFoodItems(FoodItems food) {
		return foodRepo.save(food);
	}
}
