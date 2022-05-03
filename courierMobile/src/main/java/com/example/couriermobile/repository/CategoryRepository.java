package com.example.couriermobile.repository;

import com.example.couriermobile.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByActiveIsTrue();

    Optional<Category> findByIdAndActiveTrue(Long id);
}