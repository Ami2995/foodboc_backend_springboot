package com.foodbox.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	public Set<OrderItem> findByOrderId(Long id);
}
