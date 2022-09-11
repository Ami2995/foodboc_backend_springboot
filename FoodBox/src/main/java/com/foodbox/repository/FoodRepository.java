package com.foodbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.model.FoodItems;

public interface FoodRepository extends JpaRepository<FoodItems, Long> {
	public List<FoodItems> findAllByCategoryId(Long id);
}
