package com.amazon.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.project.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
