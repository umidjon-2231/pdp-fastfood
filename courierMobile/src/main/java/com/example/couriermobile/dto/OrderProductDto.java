package com.example.couriermobile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class OrderProductDto implements Serializable {
    private final Long productId;
    private final Integer count;
}
