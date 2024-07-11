package com.ursklap.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.repositories.CategoryRepository;
import com.ursklap.ecommerce.services.base.BaseService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService extends BaseService<Category, CategoryRepository, Long> {
    // private CategoryRepository categoryRepository;
}
