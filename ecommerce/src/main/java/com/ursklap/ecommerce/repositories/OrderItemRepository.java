package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.OrderItem;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends BaseRepository<OrderItem, Long> {
}
