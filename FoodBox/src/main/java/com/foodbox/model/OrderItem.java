package com.foodbox.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = "order")
public class OrderItem {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 5000)
	private String imageUrl;
	private BigDecimal unitPrice;
	private Integer quantity;
	private Long productId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	@JsonIgnore
	private Order order;
}
