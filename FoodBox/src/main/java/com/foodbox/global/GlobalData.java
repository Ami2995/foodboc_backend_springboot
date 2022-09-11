package com.foodbox.global;

import java.util.ArrayList;
import java.util.List;

import com.foodbox.model.FoodItems;

public class GlobalData {

	public static List<FoodItems> cart;
	static {
		cart = new ArrayList<FoodItems>();
	}
}
