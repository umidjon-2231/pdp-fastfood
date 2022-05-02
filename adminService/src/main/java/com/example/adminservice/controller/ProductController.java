package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDto;
import com.example.adminservice.dto.ProductFrontDto;
import com.example.adminservice.entity.Attachment;
import com.example.adminservice.entity.Product;
import com.example.adminservice.mapper.ProductMapper;
import com.example.adminservice.repository.ProductRepository;
import com.example.adminservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductRepository productRepository;
    final ProductService productService;
    final ProductMapper productMapper;


    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(productMapper.toFrontDto(productRepository.findByActiveTrue()));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveTrue(id);
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productMapper.toFrontDto(optionalProduct.get()));
    }

    @PostMapping
    public HttpEntity<?> add(@ModelAttribute ProductDto dto){
        ApiResponse<ProductFrontDto> apiResponse = productService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@ModelAttribute ProductDto dto, @PathVariable Long id){
        ApiResponse<ProductFrontDto> apiResponse = productService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<Object> apiResponse = productService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @GetMapping("/{id}/photo")
    public HttpEntity<?> getPhoto(@PathVariable Long id){
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveTrue(id);
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Attachment photo = optionalProduct.get().getPhoto();

        return ResponseEntity.ok()
                .contentLength(photo.getSize())
                .contentType(MediaType.parseMediaType(photo.getType()))
                .body(photo.getBytes());
    }
}
