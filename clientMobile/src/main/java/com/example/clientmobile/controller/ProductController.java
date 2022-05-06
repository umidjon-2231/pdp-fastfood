package com.example.clientmobile.controller;

import com.example.clientmobile.entity.Product;
import com.example.clientmobile.mapper.ProductMapper;
import com.example.clientmobile.repository.CategoryRepository;
import com.example.clientmobile.repository.OrderProductRepository;
import com.example.clientmobile.repository.ProductRepository;
import com.example.clientmobile.service.CategoryService;
import com.example.clientmobile.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductService service;
    final ProductRepository productRepository;
    final OrderProductRepository orderProductRepository;
    final ProductMapper productMapper;

    @GetMapping("/top")
    public HttpEntity<?> getTopProducts(@RequestParam Integer limit){
        List<Long> products = orderProductRepository.findTopProducts(limit);
        List<Product> productList = productRepository.findAllById(products);
        return ResponseEntity.ok().body(productMapper.toFrontDto(productList));
    }


}
