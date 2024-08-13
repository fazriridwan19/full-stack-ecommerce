package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.CartDetail;
import com.ursklap.ecommerce.models.Order;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {
    Boolean existsByCartDetail(CartDetail cartDetail);
}
