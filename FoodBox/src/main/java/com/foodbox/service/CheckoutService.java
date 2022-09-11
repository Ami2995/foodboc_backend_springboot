package com.foodbox.service;

import java.security.Principal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.foodbox.dto.Purchase;
import com.foodbox.dto.PurchaseResponse;
import com.foodbox.model.Order;

@Service
public class CheckoutService {
	
    Principal principal;
 
	public PurchaseResponse placeOrder(Purchase purchase) {
		 // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		return UUID.randomUUID().toString();
	}
}
