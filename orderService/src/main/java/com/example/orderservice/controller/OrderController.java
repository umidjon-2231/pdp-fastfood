package com.example.orderservice.controller;

import com.example.orderservice.dto.*;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.enums.OrderStatus;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.FilialRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OrderController {
    final OrderRepository orderRepository;
    final OrderMapper orderMapper;
    final OrderService orderService;
    final FilialRepository filialRepository;

    @GetMapping
    public HttpEntity<?> getAll(
            @RequestParam(required = false, defaultValue = "") String status,
            @RequestParam(required = false) Long filial,
            @RequestParam(required = false) Boolean delivery
    ) {
        List<Order> all;
        OrderStatus orderStatus = null;
        try {
            orderStatus=OrderStatus.valueOf(status.toUpperCase());
        }catch (IllegalArgumentException ignore){}
        if(orderStatus==null && filial==null && delivery==null){
            return ResponseEntity.ok().body(orderMapper.orderToOrderFrontDto(orderRepository.findAll()));
        }
        if(delivery==null){
            if(filial==null){
                all=orderRepository.findByOrderStatus(orderStatus);
            }else {
                all=orderRepository.findByOrderStatusAndFilial_Id(orderStatus, filial);
            }

        }
        else {
            if(delivery){
                if(filial==null){
                    all=orderRepository.findByOrderStatusAndDelivery_Courier_idIsNotNull(orderStatus);
                }else {
                    all=orderRepository.findByOrderStatusAndFilial_IdAndDelivery_Courier_IdIsNotNull(orderStatus, filial);
                }
            }else{
                if(filial==null){
                    all=orderRepository.findByOrderStatusAndDelivery_Courier_idIsNull(orderStatus);
                }else {
                    all=orderRepository.findByOrderStatusAndFilial_IdAndDelivery_Courier_IdIsNull(orderStatus, filial);
                }
            }

        }

        return ResponseEntity.ok().body(orderMapper.orderToOrderFrontDto(all));
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(orderMapper.orderToOrderFrontDto(optionalOrder.get()));
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody OrderDto dto){
        ApiResponse<OrderFrontDto> apiResponse = orderService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PatchMapping("/{id}/status")
    public HttpEntity<?> changeStatus(@RequestBody Map<String, String> body, @PathVariable Long id){
        ApiResponse<Object> apiResponse = orderService.changeStatus(id,OrderStatus.valueOf(body.get("status").toUpperCase()));
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PatchMapping("/{id}/operator")
    public HttpEntity<?> changeOperator(@PathVariable Long id, @RequestBody HumanDto dto){
        ApiResponse<HumanFrontDto> apiResponse= orderService.changeOperator(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PatchMapping("/{id}/courier")
    public HttpEntity<?> changeCourier(@PathVariable Long id, @RequestBody HumanDto dto){
        ApiResponse<HumanFrontDto> apiResponse= orderService.changeCourier(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @GetMapping("/{id}/delivery")
    public HttpEntity<?> getDelivery(@PathVariable Long id){
        ApiResponse<DeliveryFrontDto> apiResponse= orderService.getDelivery(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
}
