package com.example.adminservice.repository;

import com.example.adminservice.entity.Category;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RequiredArgsConstructor
public class CategoryRepositoryTest {
//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Test
//    @Order(1)
//    public void saveCategory() {
//        Category build = Category.builder()
//                .name("Lavash")
//                .id(1L).build();
//        Category save = categoryRepository.save(build);
//        assertThat(build.getName()).isEqualTo(save.getName());
//    }
}
