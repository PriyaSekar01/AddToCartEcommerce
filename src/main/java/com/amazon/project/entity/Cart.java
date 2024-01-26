package com.amazon.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy= GenerationType. IDENTITY)
	private int id; 
	@Column(name="product_id")
	private int productId;
	
	@Column(name="productname")
	private String productName;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="amaount")
	private float amount;

}
