package com.example.clientmobile.controller;

import com.example.clientmobile.entity.Human;
import com.example.clientmobile.entity.Order;
import com.example.clientmobile.entity.enums.ClientStatus;
import com.example.clientmobile.mapper.OrderMapper;
import com.example.clientmobile.repository.HumanRepository;
import com.example.clientmobile.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    final OrderRepository orderRepository;
    final OrderMapper orderMapper;
    final HumanRepository humanRepository;

    @GetMapping()
    public HttpEntity<?> getClientOrder(@RequestParam Long client){
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, client);
        if(optionalHuman.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Order> all = orderRepository.findByClient_Id(client);
        return ResponseEntity.ok().body(orderMapper.orderToOrderDto(all));
    }

}
