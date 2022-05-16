package com.example.clientmobile.controller;

import com.example.clientmobile.entity.Category;
import com.example.clientmobile.entity.Product;
import com.example.clientmobile.mapper.ProductMapper;
import com.example.clientmobile.repository.CategoryRepository;
import com.example.clientmobile.repository.OrderProductRepository;
import com.example.clientmobile.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductRepository productRepository;
    final OrderProductRepository orderProductRepository;
    final ProductMapper productMapper;
    final CategoryRepository categoryRepository;

    @GetMapping("/top")
    public HttpEntity<?> getTopProducts(@RequestParam Integer limit){
        List<Long> products = orderProductRepository.findTopProducts(limit);
        List<Product> productList = productRepository.findAllById(products);
        return ResponseEntity.ok().body(productMapper.toFrontDto(productList));
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getProduct(@PathVariable Long id){
        Optional<Product> optionalProduct = productRepository.findByIdAndActiveTrue(id);
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(productMapper.toFrontDto(optionalProduct.get()));
    }

    @GetMapping()
    public HttpEntity<?> getProductByCategory(@RequestParam(required = false) Long category){
        if(category==null){
            return ResponseEntity.ok().body(productMapper.toFrontDto(productRepository.findAll()));
        }
        Optional<Category> optionalCategory = categoryRepository.findByIdAndActiveTrue(category);
        if(optionalCategory.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<Product> all = productRepository.findAll(Example.of(Product.builder()
                .category(optionalCategory.get())
                .build()));
        return ResponseEntity.ok().body(productMapper.toFrontDto(all));
    }

}
