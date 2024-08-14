package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutRequest {
    @NotBlank(message = "Payment method cannot be empty")
    @Size(max = 50, message = "Maximum length of payment method is 50 character")
    private String paymentMethod;

    @NotBlank(message = "Payment account cannot be empty")
    @Size(max = 255, message = "Maximum length of payment account is 255 character")
    private String paymentAccount;

    @NotBlank(message = "Cart detail IDs cannot be empty")
    private List<Long> cartDetailIds;
}
