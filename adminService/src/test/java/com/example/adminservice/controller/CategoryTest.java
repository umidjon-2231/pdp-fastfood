package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.CategoryDto;
import com.example.adminservice.entity.Category;
import com.example.adminservice.mapper.CategoryMapper;
import com.example.adminservice.mapper.CategoryMapperImpl;
import com.example.adminservice.repository.CategoryRepository;
import com.example.adminservice.repository.CategoryRepositoryTest;
import com.example.adminservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryTest {
//    @Mock
//    CategoryRepository categoryRepository;
//    @InjectMocks
//    CategoryService categoryService;
//    @Mock
//    CategoryMapper categoryMapperMock;
//    CategoryMapper categoryMapper = new CategoryMapperImpl();
//    private Category category;
//
//    @BeforeEach
//    public void setup() {
//        category = Category.builder().id(1L).name("Sirli").parent(Category.builder().id(2L).name("Lavash").build()).build();
//    }
//
//    @Test
//    @DisplayName("category_add")
//    public void save() {
//        when(categoryMapperMock.categoryDtoToCategory(any(CategoryDto.class)))
//                .then(invocation -> categoryMapper
//                        .categoryDtoToCategory(invocation.getArgument(0, CategoryDto.class)));
//        given(categoryRepository.save(any(Category.class))).willReturn(category);
//        ApiResponse<Category> add = categoryService.add(new CategoryDto(category.getName(), category.getParent() != null ? category.getParent().getId() : null));
//        assertThat(add.isSuccess()).isTrue();
//        assertThat(add.getData()).isEqualTo(category);
//    }

}
