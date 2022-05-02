package com.example.adminservice.dto;

import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Category;
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
