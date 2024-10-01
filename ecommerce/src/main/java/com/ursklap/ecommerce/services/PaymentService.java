package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.models.Payment;
import com.ursklap.ecommerce.repositories.PaymentRepository;
import com.ursklap.ecommerce.services.base.BaseService;
import com.ursklap.ecommerce.utils.specifications.PaymentSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService extends BaseService<Payment, PaymentRepository, Long> {
    private PaymentRepository paymentRepository;

    public List<Payment> findAll(String type) {
        if (type != null) {
            Specification<Payment> filterCriteria = Specification.where(PaymentSpecification.hasType(type));
            return this.paymentRepository.findAll(filterCriteria);
        }
        return super.findAll();
    }
}
