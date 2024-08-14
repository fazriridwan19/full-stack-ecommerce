package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.dto.responses.CartResponse;
import com.ursklap.ecommerce.models.Cart;
import com.ursklap.ecommerce.models.CartDetail;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends BaseRepository<CartDetail, Long> {
    @Query("SELECT new com.ursklap.ecommerce.dto.responses.CartResponse(c.id, cd.id, cd.quantity, cd.totalPrice, cd.totalDiscountedPrice, new com.ursklap.ecommerce.dto.responses.ProductResponse(" +
                "p.id, p.name, " +
                "p.code, p.description, " +
                "p.price, p.discountedPrice, " +
                "p.isInStock, p.stock, " +
                "new com.ursklap.ecommerce.dto.responses.CategoryResponse(cat.id, cat.name, cat.description, cat.imageUrl))) " +
            "FROM CartDetail cd " +
            "JOIN cd.product as p " +
            "LEFT JOIN p.category as cat " +
            "JOIN cd.cart as c " +
            "WHERE c.id = :cartId")
    List<CartResponse> findCartDetailsByCurrentUserCart(@Param("cartId") Long cartId);

    @Query("SELECT new com.ursklap.ecommerce.dto.responses.CartResponse(" +
            "c.id, cd.id, " +
            "cd.quantity, cd.totalPrice, cd.totalDiscountedPrice, " +
            "new com.ursklap.ecommerce.dto.responses.ProductResponse(" +
                "p.id, p.name, " +
                "p.code, p.description, " +
                "p.price, p.discountedPrice, " +
                "p.isInStock, p.stock, " +
                "new com.ursklap.ecommerce.dto.responses.CategoryResponse(cat.id, cat.name, cat.description, cat.imageUrl))) " +
            "FROM CartDetail cd " +
            "JOIN cd.product as p " +
            "LEFT JOIN p.category as cat " +
            "JOIN cd.cart as c " +
            "WHERE cd.id = :id")
    Optional<CartResponse> findCartDetailById(@Param("id") Long id);

    Optional<CartDetail> findByCartAndProduct(Cart cart, Product product);
}
