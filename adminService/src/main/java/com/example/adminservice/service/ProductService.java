package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDto;
import com.example.adminservice.dto.ProductFrontDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.Product;
import com.example.adminservice.mapper.ProductMapper;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final ProductMapper productMapper;
    final CategoryRepository categoryRepository;


    public ApiResponse<ProductFrontDto> add(ProductDto dto){
        if(dto.getPhoto().isEmpty()){
            return ApiResponse.<ProductFrontDto>builder()
                    .message("Photo shouldn't be empty")
                    .build();
        }
        if(!Objects.requireNonNull(dto.getPhoto().getOriginalFilename()).matches("^(.+)\\.(png|jpeg|ico|jpg)$")){
            return ApiResponse.<ProductFrontDto>builder()
                    .message("Photo type must be png, jpeg, ico, jpg")
                    .build();
        }
        Product product = productMapper.productDtoToProduct(dto);
        Optional<Category> optionalCategory = categoryRepository.findByIdAndActiveTrue(dto.getCategoryId());
        product.setCategory(optionalCategory.orElse(product.getCategory()));
        Product save = productRepository.save(product);
        return ApiResponse.<ProductFrontDto>builder()
                .success(true)
                .data(productMapper.toFrontDto(save))
                .message("Added!")
                .build();
    }

    public ApiResponse<ProductFrontDto> edit(Long id, ProductDto dto){
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveTrue(id);
        if(optionalProduct.isEmpty()){
            return ApiResponse.<ProductFrontDto>builder()
                    .message("Product with id=(" + id+") not found")
                    .build();
        }
        Product product = optionalProduct.get();
        if(dto.getPhoto().isEmpty()){
            return ApiResponse.<ProductFrontDto>builder()
                    .message("Photo shouldn't be empty")
                    .build();
        }
        if(!Objects.requireNonNull(dto.getPhoto().getOriginalFilename()).matches("^(.+)\\.(png|jpeg|ico|jpg)$")){
            return ApiResponse.<ProductFrontDto>builder()
                    .message("Photo type must be png, jpeg, ico, jpg")
                    .build();
        }
        productMapper.updateProductFromProductDto(dto, product);
        Product save = productRepository.save(product);
        return ApiResponse.<ProductFrontDto>builder()
                .success(true)
                .data(productMapper.toFrontDto(save))
                .message("Edited!")
                .build();
    }


    public ApiResponse<Object> delete(Long id){
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveTrue(id);
        if(optionalProduct.isEmpty()){
            return ApiResponse.builder()
                    .message("Product with id=(" + id + ") not found")
                    .build();
        }
        optionalProduct.get().setActive(false);
        productRepository.save(optionalProduct.get());
        return ApiResponse.builder()
                .success(true)
                .message("Deleted!")
                .build();
    }
}
