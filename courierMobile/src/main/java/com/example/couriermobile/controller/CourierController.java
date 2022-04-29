package com.example.couriermobile.controller;

import com.example.couriermobile.entity.Order;
import com.example.couriermobile.feign.OrderFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courier")
@RequiredArgsConstructor
public class CourierController {

    final OrderFeign orderFeign;

    //TODO order entityga yetkazib beriladingan joy adresini qo'shish
    //TODO courier activeligini o'zi boshqara olishi kerak
    //TODO courierga buyurmatalarining holati ko'rinishi lozim
    //TODO courierning buyurmalar tarixi bo'lishi kerak
    //TODO courier barcha buyurtmalarni ko'rib istaganini tanlab yetkazib berishi mumkin

    @GetMapping()
    public ResponseEntity getAllOrders(){
        List<Order> allOrders = orderFeign.getAllOrders();
        return ResponseEntity.ok(allOrders);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneOrder(@PathVariable Long id){
        Order order = orderFeign.getOneOrder(id);
        return ResponseEntity.ok(order);
    }
}
