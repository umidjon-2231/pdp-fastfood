package com.example.clientmobile.dto;

import com.example.clientmobile.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductFrontDto {
    private String nameUz, nameRu, description;
    private AttachmentDto photo;
    private BigDecimal price;
    private Category category;
}
