package com.amazon.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Integer prodectId;
		
	@Column(name="name")
	private String name;
	
	@Column(name="quatity")
	private int availableQuatity;
	
    @Column(name="price")
	private float price;
	
	@Override
	public String toString() {
		return "product{"+
	"id=" + prodectId +
	",name="+ name +
	",quatity=" + availableQuatity +
	",price=" + price +
	'}';
	}
	public Product(String name,int availableQuatity,float price) {
		this.name=name;
		this.availableQuatity=availableQuatity;
		this.price=price;
	}
	
	

}
