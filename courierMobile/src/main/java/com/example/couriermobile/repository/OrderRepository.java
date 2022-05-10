package com.example.couriermobile.repository;

import com.example.couriermobile.entity.Order;
import com.example.couriermobile.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderStatus(OrderStatus orderStatus);
    List<Order> findByOrderStatusAndDelivery_Courier_idIsNull(OrderStatus orderStatus);
    List<Order> findByOrderStatusAndFilial_Id(OrderStatus orderStatus, Long filial_id);
    List<Order> findByOrderStatusAndFilial_IdAndDelivery_Courier_IdIsNull(OrderStatus orderStatus, Long filial_id);
    List<Order> findByOrderStatusAndDelivery_Courier_idIsNotNull(OrderStatus orderStatus);
    List<Order> findByOrderStatusAndFilial_IdAndDelivery_Courier_IdIsNotNull(OrderStatus orderStatus, Long filial);
    List<Order> findByDelivery_Courier_Id(Long courier_id);
}