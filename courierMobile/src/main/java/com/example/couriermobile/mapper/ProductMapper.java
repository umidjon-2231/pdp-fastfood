package com.example.couriermobile.mapper;

import com.example.couriermobile.dto.CategoryDto;
import com.example.couriermobile.dto.ProductDto;
import com.example.couriermobile.entity.Category;
import com.example.couriermobile.entity.Product;
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
