package com.example.couriermobile.controller;

import com.example.couriermobile.dto.ApiResponse;
import com.example.couriermobile.dto.CourierEditDto;
import com.example.couriermobile.dto.HumanFrontDto;
import com.example.couriermobile.entity.Human;
import com.example.couriermobile.entity.enums.ClientStatus;
import com.example.couriermobile.mapper.HumanMapper;
import com.example.couriermobile.mapper.OrderMapper;
import com.example.couriermobile.repository.HumanRepository;
import com.example.couriermobile.repository.OrderRepository;
import com.example.couriermobile.service.CourierService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CourierController {
    final HumanRepository humanRepository;
    final HumanMapper humanMapper;
    final OrderRepository orderRepository;
    final OrderMapper orderMapper;
    final CourierService courierService;

    @GetMapping("/{number}")
    public HttpEntity<?> getSelf(@PathVariable String number){
        Optional<Human> optionalHuman = humanRepository.findByNumber(number);
        if(optionalHuman.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(humanMapper.toDto(optionalHuman.get()));
    }

    @GetMapping("/{id}/orders")
    public HttpEntity<?> getOrders(
            @PathVariable Long id,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ){
        Optional<Human> optionalHuman = humanRepository.findByStatusIsNotAndId(ClientStatus.DELETED, id);
        if(optionalHuman.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if(from==null && to==null){
            return ResponseEntity.ok().body(orderMapper.orderToOrderFrontDto(orderRepository.findByDelivery_Courier_Id(id)));
        }else if(from!=null && to!=null){
            return ResponseEntity.ok().body(orderMapper.orderToOrderFrontDto(
                    orderRepository.findByDelivery_Courier_IdAndTimeIsBetween(id,
                            LocalDateTime.of(from, LocalTime.parse("00:00")),
                            LocalDateTime.of(to, LocalTime.parse("23:59")))
            ));
        }
        if(from!=null){
            return ResponseEntity.ok().body(orderMapper.orderToOrderFrontDto(
                    orderRepository.findByDelivery_Courier_IdAndTimeIsBetween(id,
                            LocalDateTime.of(from, LocalTime.parse("00:00")), LocalDateTime.now())
            ));
        }
        return ResponseEntity.ok().body(orderMapper.orderToOrderFrontDto(
                orderRepository.findByDelivery_Courier_Id(id)
        ));
    }

    @PutMapping("/{id}/photo")
    public HttpEntity<?> getPhoto(@PathVariable Long id, MultipartHttpServletRequest req){
        ApiResponse<HumanFrontDto> apiResponse = courierService.changePhoto(id, req.getFile("photo"));
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editData(@PathVariable Long id, @RequestBody CourierEditDto dto){
        ApiResponse<HumanFrontDto> apiResponse= courierService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

}
