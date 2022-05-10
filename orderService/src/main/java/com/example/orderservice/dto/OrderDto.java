package com.example.orderservice.dto;

import com.example.orderservice.entity.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDto implements Serializable {
    private final List<OrderProductDto> products;
    private final PayType payType;
    private final DeliveryDto delivery;
    private final Long filialId;
    private final Long clientId;
}
