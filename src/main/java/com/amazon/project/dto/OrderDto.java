package com.amazon.project.dto;

import java.util.List;

import com.amazon.project.entity.Cart;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
	
	private String orderDescription;
	
	private List<Cart> cartItems;
	
	private String email;
	
	private String name;

}
