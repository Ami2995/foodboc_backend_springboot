package com.foodbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodbox.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
