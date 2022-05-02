package com.example.adminservice.repository;

import com.example.adminservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndActiveTrue(Long id);

    List<Product> findByActiveTrue();
}