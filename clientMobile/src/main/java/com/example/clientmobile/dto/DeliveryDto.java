package com.example.clientmobile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class DeliveryDto implements Serializable {
    private final BigDecimal price;
    private final Float longitude;
    private final Float latitude;
    private final HumanFrontDto courier;
    private final String address;
}
