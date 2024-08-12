package com.ursklap.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckoutRequest {
    @NotBlank(message = "Cart detail ID cannot be empty")
    private Long cartDetailId;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50, message = "Maximum length of email is 50 character")
    private String email;
}
