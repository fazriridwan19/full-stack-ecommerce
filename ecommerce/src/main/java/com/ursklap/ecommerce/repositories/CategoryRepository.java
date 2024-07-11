package com.ursklap.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.repositories.base.BaseRepository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long>{
    
}
