package com.ursklap.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.ProductRepository;
import com.ursklap.ecommerce.services.base.BaseService;

@Service
public class ProductService extends BaseService<Product, ProductRepository, Long>{
    
}
