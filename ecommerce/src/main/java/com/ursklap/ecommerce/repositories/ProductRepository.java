package com.ursklap.ecommerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ursklap.ecommerce.dto.responses.ProductResponse;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.base.BaseRepository;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {
    @Query("SELECT NEW com.ursklap.ecommerce.dto.responses.ProductResponse(" +
            "p.id,p.name," +
            "p.code,p.description," +
            "p.price,p.discountedPrice," +
            "p.isInStock,p.stock," +
            "NEW com.ursklap.ecommerce.dto.responses.CategoryResponse(c.id, c.name, c.description, c.imageUrl))" +
            "FROM Product p LEFT JOIN p.category as c")
    List<ProductResponse> findAllProductDto();

    @Query("SELECT NEW com.ursklap.ecommerce.dto.responses.ProductResponse(" +
            "p.id,p.name," +
            "p.code,p.description," +
            "p.price,p.discountedPrice," +
            "p.isInStock,p.stock," +
            "NEW com.ursklap.ecommerce.dto.responses.CategoryResponse(c.id, c.name, c.description, c.imageUrl))" +
            "FROM Product p LEFT JOIN p.category as c " +
            "WHERE p.id = :id")
    Optional<ProductResponse> findByIdProductDto(@Param("id") Long id);
}
