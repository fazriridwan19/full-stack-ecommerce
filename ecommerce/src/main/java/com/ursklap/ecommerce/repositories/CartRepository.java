package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.Cart;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends BaseRepository<Cart, Long> {
}
