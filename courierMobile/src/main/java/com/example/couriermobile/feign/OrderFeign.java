package com.example.couriermobile.feign;

import com.example.couriermobile.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "orderService", url = "localhost:8085/")
public interface OrderFeign {

    @GetMapping("order")
    List<Order> getAllOrders();

    @GetMapping("order/{id}")
    Order getOneOrder(@PathVariable Long id);
}
