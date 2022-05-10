package com.example.orderservice.mapper;

import com.example.orderservice.dto.AttachmentDto;
import com.example.orderservice.dto.CategoryDto;
import com.example.orderservice.dto.HumanFrontDto;
import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.entity.Category;
import com.example.orderservice.entity.Human;
import com.example.orderservice.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AttachmentMapper.class})
public interface ProductMapper {
    CategoryDto toDto(Category category);

    ProductDto toDto(Product product);

    @AfterMapping
    default void url(@MappingTarget ProductDto productDto, Product product){
        productDto.getPhoto().setUrl("/api/admin/product/"+product.getId()+"/photo");
    }
    @Named("url")
    default String urlGenerate(Long id){
        return "/api/admin/product/"+id+"/photo";
    }
}
