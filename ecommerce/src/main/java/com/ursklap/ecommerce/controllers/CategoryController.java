package com.ursklap.ecommerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ursklap.ecommerce.dto.CategoryDto;
import com.ursklap.ecommerce.dto.responses.ApiResponse;
import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.services.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> create(@RequestBody CategoryDto request) {
        Category category = this.categoryService.create(
                Category.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .imageUrl(request.getImageUrl())
                        .build());
        ApiResponse<CategoryDto> response = new ApiResponse<CategoryDto>();
        response.setData(
                CategoryDto.builder()
                        .name(category.getName())
                        .description(category.getDescription())
                        .imageUrl(category.getImageUrl())
                        .build());
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Category successfully created");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
