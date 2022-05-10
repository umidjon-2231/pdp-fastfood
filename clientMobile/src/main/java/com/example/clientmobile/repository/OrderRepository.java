package com.example.clientmobile.repository;

import com.example.clientmobile.entity.Human;
import com.example.clientmobile.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClient_Id(Long client_id);
}