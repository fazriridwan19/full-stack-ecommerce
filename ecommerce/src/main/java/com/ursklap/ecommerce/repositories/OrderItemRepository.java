package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.dto.responses.OrderItemResponse;
import com.ursklap.ecommerce.models.OrderItem;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {
    @Query("SELECT new com.ursklap.ecommerce.dto.responses.OrderItemResponse(" +
            "new com.ursklap.ecommerce.dto.responses.ProductResponse(" +
                "p.id, p.name, " +
                "p.code, p.description, " +
                "p.price, p.discountedPrice, " +
                "p.isInStock, p.stock, " +
                "new com.ursklap.ecommerce.dto.responses.CategoryResponse(cat.id, cat.name, cat.description, cat.imageUrl)), " +
            "oi.quantity, oi.totalPrice)" +
            "FROM OrderItem oi JOIN oi.order as o JOIN oi.product as p JOIN p.category as cat WHERE o.id = :orderId")
    List<OrderItemResponse> findAllByOrderIdAsDto(@Param("orderId") Long orderId);
}
