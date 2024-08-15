package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutRequest {
    @NotBlank(message = "Payment ID cannot be empty")
    private Long paymentId;

    @NotBlank(message = "Cart detail IDs cannot be empty")
    private List<Long> cartDetailIds;
}
