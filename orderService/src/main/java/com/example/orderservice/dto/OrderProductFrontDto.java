package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderProductFrontDto implements Serializable {
    private final ProductDto product;
    private final Integer count;
    private final BigDecimal price, amount;
}
