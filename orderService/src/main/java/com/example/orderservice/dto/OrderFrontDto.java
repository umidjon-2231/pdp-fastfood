package com.example.orderservice.dto;

import com.example.orderservice.entity.enums.OrderStatus;
import com.example.orderservice.entity.enums.PayType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderFrontDto implements Serializable {
    private final LocalDateTime time;
    private final List<OrderProductFrontDto> products;
    private final HumanFrontDto operator, client;
    private final PayType payType;
    private final DeliveryFrontDto delivery;
    private final FilialDto filial;
    private final OrderStatus orderStatus;
    private final BigDecimal amount;
}
