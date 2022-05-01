package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;
    final CategoryRepository categoryRepository;

    @GetMapping
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(categoryRepository.findByActiveIsTrue());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty() || !optionalCategory.get().isActive()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(optionalCategory.orElseThrow());
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CategoryDto dto){
        ApiResponse<Category> apiResponse = categoryService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@RequestBody CategoryDto dto, @PathVariable Long id){
        ApiResponse<Category> apiResponse = categoryService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse<Object> apiResponse = categoryService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:400).body(apiResponse);
    }
}
