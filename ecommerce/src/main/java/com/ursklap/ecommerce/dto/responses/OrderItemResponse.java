package com.ursklap.ecommerce.dto.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponse {
    private ProductResponse product;
    private Integer quantity;
    private Integer totalPrice;
}
