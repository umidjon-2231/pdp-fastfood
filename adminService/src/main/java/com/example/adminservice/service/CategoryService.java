package com.example.adminservice.service;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.dto.HumanDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.entity.Human;
import com.example.adminservice.mapper.CategoryMapper;
import com.example.adminservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryMapper categoryMapper;
    final CategoryRepository categoryRepository;

    public ApiResponse<Category> add(CategoryDto dto) {
        Category category = categoryMapper.categoryDtoToCategory(dto);
        if(dto.getParentId()==null){
            category.setParent(null);
        }
        Category save = categoryRepository.save(category);
        return ApiResponse.<Category>builder()
                .success(true)
                .data(save)
                .message("Added!")
                .build();
    }

    public ApiResponse<Category> edit(Long id, CategoryDto dto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty() || !optionalCategory.get().isActive()) {
            return ApiResponse.<Category>builder()
                    .message("Category with id=(" + id + ") not found")
                    .build();
        }
        Category category = optionalCategory.get();
        categoryMapper.updateCategoryFromCategoryDto(dto, category);
        if(dto.getParentId()==null){
            category.setParent(null);
        }
        Category save = categoryRepository.save(category);
        return ApiResponse.<Category>builder()
                .success(true)
                .data(save)
                .message("Edited!")
                .build();
    }

    public ApiResponse<Object> delete(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty() || !optionalCategory.get().isActive()) {
            return ApiResponse.builder()
                    .message("Category with id=(" + id + ") not found")
                    .build();
        }
        optionalCategory.get().setActive(false);
        categoryRepository.save(optionalCategory.get());
        return ApiResponse.builder()
                .success(true)
                .message("Deleted!")
                .build();
    }
}
