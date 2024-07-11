package com.ursklap.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.base.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long>{
    
}
