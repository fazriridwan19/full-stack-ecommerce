package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.CartDetail;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends BaseRepository<CartDetail, Long> {
}
