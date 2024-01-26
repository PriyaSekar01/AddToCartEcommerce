package com.amazon.project.entity;



import java.util.Optional;

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
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String userGender;
	
	@Column(name="email")
	private String email;
	
	@Column(name="number")
	private String mobileNumber;
	
	@Column(name="password")
	private String passWord;

	public User(String name, String userGender, String email, String mobileNumber, String passWord) {
		super();
		
		this.name = name;
		this.userGender = userGender;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.passWord = passWord;
	}
	public User(String email,String passWord) {
		this.email=email;
		this.passWord=passWord;
	}
	
	

	
    

}
