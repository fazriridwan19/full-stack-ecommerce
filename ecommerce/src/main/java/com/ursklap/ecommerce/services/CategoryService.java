package com.ursklap.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ursklap.ecommerce.dto.CategoryDto;
import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.repositories.CategoryRepository;
import com.ursklap.ecommerce.services.base.BaseService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryService extends BaseService<Category, CategoryRepository, Long> {

    public Category create(CategoryDto categoryDto) {
        log.info("Saving category to database");
        return super.create(
                Category.builder()
                        .name(categoryDto.getName())
                        .description(categoryDto.getDescription())
                        .imageUrl(categoryDto.getImageUrl())
                        .build());
    }

    public List<CategoryDto> findAllCategory() {
        log.info("Fetching categories from database");
        List<Category> categories = super.findAll();
        return categories.stream().map(category -> {
            return CategoryDto.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .description(category.getDescription())
                    .imageUrl(category.getImageUrl())
                    .build();
        }).collect(Collectors.toList());
    }
}
