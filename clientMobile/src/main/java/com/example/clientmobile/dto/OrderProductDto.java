package com.example.clientmobile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class OrderProductDto implements Serializable {
    private final Long productId;
    private final Integer count;
    private final BigDecimal price;
}