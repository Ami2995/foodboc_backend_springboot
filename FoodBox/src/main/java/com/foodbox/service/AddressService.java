package com.foodbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.model.Address;
import com.foodbox.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addRepo;
	
	public Address addAddress(Address address) {
		return addRepo.save(address);
	}
	
	public List<Address> getAllAddress(){
		return addRepo.findAll();
	}
	
	public Address getAddressById(Long id) {
		return addRepo.findById(id).get();
	}
	
	public void deleteAddress(Long id) {
		addRepo.deleteById(id);
	}
}
