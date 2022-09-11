package com.foodbox.model;

import lombok.Data;

@Data
public class JWTRequest {

	private String username;
	private String password;
}
