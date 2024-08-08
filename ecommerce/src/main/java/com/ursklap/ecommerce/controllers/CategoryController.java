package com.ursklap.ecommerce.controllers;

import java.util.List;
import java.util.random.RandomGenerator;

import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ursklap.ecommerce.dto.CategoryDto;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.services.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {
    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<ResponseDto<Category>> create(@RequestBody @Valid CategoryDto request) {
        Category category = this.categoryService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(category, HttpStatus.CREATED.value(), "Category successfully created"));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CategoryDto>>> getAllCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.categoryService.findAllCategory(), HttpStatus.OK.value(), "Successfully retrieve categories"));

    }

    @PostMapping("/request/test")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "Authorization")
    public ResponseEntity<ResponseDto<String>> createBulk() {
        for (int i = 0; i < 1000; i++) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;

            RandomGenerator random = RandomGenerator.of("L32X64MixRandom");

            String name = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            CategoryDto request = CategoryDto.builder().name(name).description("test").imageUrl("tes").build();
            this.categoryService.create(request);
        }
        ResponseDto<String> response = new ResponseDto<String>();
        response.setData("Success");
        response.setStatus(HttpStatus.CREATED.value());
        response.setMessage("Category successfully created");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
