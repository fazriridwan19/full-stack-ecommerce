package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.OrderHistory;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends BaseRepository<OrderHistory, Long> {
}
