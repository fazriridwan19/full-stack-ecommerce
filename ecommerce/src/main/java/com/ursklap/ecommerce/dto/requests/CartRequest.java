package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    @NotBlank(message = "Product id cannot be empty")
    private Long productId;

    @NotBlank(message = "Quantity product cannot be empty")
    private Integer quantity;
}
