package com.example.clientmobile.service;

import com.example.clientmobile.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
}
