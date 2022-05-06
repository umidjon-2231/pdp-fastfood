package com.example.clientmobile.mapper;

import com.example.clientmobile.dto.ProductFrontDto;
import com.example.clientmobile.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AttachmentMapper.class})
public interface ProductMapper {

    @Mapping(source = "photo.id", target = "photo.url", qualifiedByName = "url")
    ProductFrontDto toFrontDto(Product product);
    List<ProductFrontDto> toFrontDto(List<Product> products);

    @Named("url")
    default String urlGenerate(Long id){
        return "/api/product/"+id+"/photo";
    }
}
