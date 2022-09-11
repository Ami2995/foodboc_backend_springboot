package com.foodbox.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class FoodItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long id;
	private String foodName;
	private double foodPrice;
	private String foodDescription;
	private String foodImage;
	private String timeToCook;
	private int foodRating;
	private boolean isAvailable = true;
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
}
