package com.example.adminservice.mapper;

import com.example.adminservice.dto.ProductDto;
import com.example.adminservice.dto.ProductFrontDto;
import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", uses = {AttachmentMapper.class})
public interface ProductMapper {
    @Mapping(source = "categoryId", target = "category.id")
    Product productDtoToProduct(ProductDto productDto);

//    @Mapping(source = "category.id", target = "categoryId")
//    ProductDto productToProductDto(Product product);
    @Mapping(source = "photo.id", target = "photo.url", qualifiedByName = "url")
    ProductFrontDto toFrontDto(Product product);
    List<ProductFrontDto> toFrontDto(List<Product> products);

    @Mapping(source = "categoryId", target = "category.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromProductDto(ProductDto productDto, @MappingTarget Product product);

    @Named("url")
    default String urlGenerate(Long id){
        return "/api/product/"+id+"/photo";
    }
}
