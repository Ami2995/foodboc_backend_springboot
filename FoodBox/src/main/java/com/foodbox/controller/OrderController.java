package com.foodbox.controller;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.dto.Purchase;
import com.foodbox.dto.PurchaseResponse;
import com.foodbox.model.Order;
import com.foodbox.model.OrderItem;
import com.foodbox.model.User;
import com.foodbox.repository.OrderItemRepository;
import com.foodbox.repository.OrderRepository;
import com.foodbox.repository.UserRepository;
import com.foodbox.service.CheckoutService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/checkout")
public class OrderController {

	@Autowired
	 private CheckoutService checkoutService;
	 
    @Autowired
    private UserRepository userRepo;
  
    @Autowired
    private OrderItemRepository itemRepo;
    
    @Autowired
    private OrderRepository orderRepo;
    
	@PostMapping("/")
	public PurchaseResponse placeOrder(Principal principal, @RequestBody Purchase purchase){
		Order order = purchase.getOrder();
		// populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setAddress(purchase.getAddress());
        
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        
        String username = principal.getName();
        User user = userRepo.findUserByEmail(username);
        order.setUser(userRepo.findById(user.getId()).get());
        // populate customer with order
        user.add(order);

        // save to the database
        userRepo.save(user);

		 PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
	        return purchaseResponse;
	}
	
	private String generateOrderTrackingNumber() {
		return UUID.randomUUID().toString();
	}
	
	
	@GetMapping("/{id}")
	public Set<OrderItem> findById(@PathVariable("id") Long id){
		return itemRepo.findByOrderId(id);
	}
	
	@GetMapping("/order/{emailAddress}")
	public Set<Order> findByEmail(@PathVariable("emailAddress") String email){
		return orderRepo.findByUserEmailAddress(email);
	}
	
	@GetMapping("/")
	public List<Order> findAllOrder(){
		return orderRepo.findAll();
	}
	
	 
}
