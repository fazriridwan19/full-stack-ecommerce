package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.Payment;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends BaseRepository<Payment, Long> {
    Optional<Payment> findByAccount(String account);
}
