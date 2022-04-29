package com.example.adminservice.controller;


import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.ProductDTO;
import com.example.adminservice.mapper.ProductMapper;
import com.example.adminservice.repositary.ProductRepositary;
import com.example.adminservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    final ProductRepositary productRepository;
    final ProductService productService;
    final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) {
        ApiResponse add = productService.add(productDTO);
        return ResponseEntity.ok().body(add);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ApiResponse response = productService.getOne(id);
        return ResponseEntity.ok().body(response);
    }



}

