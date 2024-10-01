package com.ursklap.ecommerce.utils.specifications;

import com.ursklap.ecommerce.models.Payment;
import org.springframework.data.jpa.domain.Specification;

public class PaymentSpecification {
    private PaymentSpecification () {}

    public static Specification<Payment> hasType(String type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), type));
    }
}
