package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfileUpdateRequest {
    @Size(max = 20)
    private String name;

    @Size(max = 50)
    private String email;

    @Size(max = 15)
    private String phoneNumber;
}
