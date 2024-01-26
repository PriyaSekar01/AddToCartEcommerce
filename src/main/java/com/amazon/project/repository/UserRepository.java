package com.amazon.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmailAndPassWord(String email, String passWord);

	User getByEmail(String email);




}
