package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailUpdateRequest {
    @NotBlank(message = "Cart detail ID cannot be empty")
    private Long cartDetailId;

    @NotBlank(message = "Quantity product cannot be empty")
    private Integer quantity;
}
