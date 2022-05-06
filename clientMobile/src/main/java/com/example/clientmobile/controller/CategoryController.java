package com.example.clientmobile.controller;

import com.example.clientmobile.dto.CategoryChildrenDto;
import com.example.clientmobile.entity.Category;
import com.example.clientmobile.repository.CategoryRepository;
import com.example.clientmobile.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryRepository categoryRepository;
    final CategoryService service;

    @GetMapping
    public HttpEntity<?> getCategory(){
        List<Category> categories = categoryRepository.findByActiveIsTrue();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Long id,
                                        @RequestParam(required = false, defaultValue = "false") boolean children){
        Optional<Category> optionalCategory = categoryRepository.findByIdAndActiveTrue(id);
        if(optionalCategory.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        if (children) {
            List<CategoryChildrenDto> result = service.getChildren(id);
            return ResponseEntity.ok().body(CategoryChildrenDto.builder()
                    .children(result)
                    .parent(optionalCategory.get().getParent())
                    .name(optionalCategory.get().getName())
                    .id(optionalCategory.get().getId())
                    .build());
        }
        return ResponseEntity.ok().body(optionalCategory.get());
    }

    @GetMapping("/parent")
    public HttpEntity<?> getAllCategoryParent(){
        List<Category> categories = categoryRepository.findByParentNullAndActiveTrue();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}/child")
    public HttpEntity<?> getCategoryChild(@PathVariable Long id){
        List<Category> categories = categoryRepository.findByParent_IdAndActiveTrue(id);
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}/children")
    public HttpEntity<?> getCategoryChildren(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryRepository.findByIdAndActiveTrue(id);
        if(optionalCategory.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        List<CategoryChildrenDto> result = service.getChildren(id);
        return ResponseEntity.ok().body(result);
    }
}
