package com.foodbox.dto;

import java.util.Set;

import com.foodbox.model.Address;
import com.foodbox.model.Order;
import com.foodbox.model.OrderItem;
import com.foodbox.model.User;

import lombok.Data;

@Data
public class Purchase {
  private User user;
  private Address address;
  private Order order;
  private Set<OrderItem> orderItems;
}
