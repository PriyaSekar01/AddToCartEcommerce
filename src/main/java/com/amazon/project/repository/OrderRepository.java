package com.amazon.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.project.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer >{

}
