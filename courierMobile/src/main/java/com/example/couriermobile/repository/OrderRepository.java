package com.example.couriermobile.repository;

import com.example.couriermobile.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}