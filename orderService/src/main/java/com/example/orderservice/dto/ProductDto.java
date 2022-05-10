package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class ProductDto implements Serializable {
    private final String nameUz;
    private final String nameRu;
    private final BigDecimal price;
    private final String description;
    private final CategoryDto category;
    private final AttachmentDto photo;
}
