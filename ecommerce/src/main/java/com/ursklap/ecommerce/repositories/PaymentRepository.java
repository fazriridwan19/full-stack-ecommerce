package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.Payment;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends BaseRepository<Payment, Long> {
}
