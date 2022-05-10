package com.example.clientmobile.dto;

import com.example.clientmobile.entity.enums.OrderStatus;
import com.example.clientmobile.entity.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDto implements Serializable {
    private final LocalDateTime time;
    private final List<OrderProductDto> products;
    private final HumanFrontDto operator;
    private final PayType payType;
    private final DeliveryDto delivery;
    private final FilialDto filial;
    private final OrderStatus orderStatus;
    private final HumanFrontDto client;
}
