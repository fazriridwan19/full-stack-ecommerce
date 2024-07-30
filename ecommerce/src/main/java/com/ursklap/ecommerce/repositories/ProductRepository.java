package com.ursklap.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ursklap.ecommerce.dto.responses.ProductResponse;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.base.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    @Query("SELECT NEW com.ursklap.ecommerce.dto.responses.ProductResponse(p.id as id, p.name as name, p.code as code, p.description as description, p.price as price, p.discountedPrice as discountedPrice, p.isInStock as isInStock, p.stock as stock, category) FROM Product p LEFT JOIN p.category as category")
    List<ProductResponse> findAllProductDto();

    @Query("SELECT p FROM Product p")
    List<Product> findAllProduct();
}
