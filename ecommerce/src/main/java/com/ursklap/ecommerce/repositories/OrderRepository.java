package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.dto.responses.OrderResponse;
import com.ursklap.ecommerce.models.Order;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    @Query("SELECT new com.ursklap.ecommerce.dto.responses.OrderResponse(" +
            "o.id, o.code, o.orderDate, o.orderTime, " +
            "p.type, p.account, p.name, " +
            "o.paymentDate, o.paymentTime, o.totalAmount, s.name) " +
            "FROM Order o JOIN o.currentStatus as s JOIN o.payment as p WHERE o.id = :id")
    public Optional<OrderResponse> findByIdAsDto(@Param("id") Long id);

    public List<Order> findAllByUserId(Long userId);
}
