package com.example.clientmobile.repository;

import com.example.clientmobile.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}