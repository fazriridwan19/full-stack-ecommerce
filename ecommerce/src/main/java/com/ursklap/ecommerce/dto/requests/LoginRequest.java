package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "Username cannot be empty")
    @Size(max = 25, message = "Maximum number of username is 25")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
