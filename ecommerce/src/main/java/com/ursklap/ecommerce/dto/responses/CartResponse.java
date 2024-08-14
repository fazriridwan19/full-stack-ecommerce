package com.ursklap.ecommerce.dto.responses;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private Long cartId;

    private Long cartDetailId;

    private Integer quantity;

    private Integer totalPrice;

    private Integer totalDiscountedPrice;

    private ProductResponse product;
}
