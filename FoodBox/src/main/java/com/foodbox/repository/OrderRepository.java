package com.foodbox.repository;


import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.model.Order;

//@CrossOrigin("http://localhost:4200/")
//@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order, Long> {
	
//	  public Page<Order> findByUserEmailAddress(@Param("emailAddress") String emailAddress, Pageable pageable); 
	  
	public Set<Order> findByUserEmailAddress(String emailAddress);
}
