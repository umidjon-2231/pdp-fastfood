package com.example.clientmobile.service;

import com.example.clientmobile.dto.CategoryChildrenDto;
import com.example.clientmobile.entity.Category;
import com.example.clientmobile.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;

    public List<CategoryChildrenDto> getChildren(Long id){
        List<Category> categories = categoryRepository.findByParent_IdAndActiveTrue(id);
        List<CategoryChildrenDto> result=new ArrayList<>();
        for (Category category : categories) {
            result.add(getChildren(category));
        }
        return result;
    }

    public CategoryChildrenDto getChildren(Category category){
        CategoryChildrenDto result=CategoryChildrenDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        List<Category> categories = categoryRepository.findByParent_IdAndActiveTrue(category.getId());
        if(categories.isEmpty()){
            return result;
        }
        for (Category child : categories) {
            result.getChildren().add(getChildren(child));
        }
        return result;
    }
}
