package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 20)
    private String name;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 20)
    private String email;

    @Size(max = 100)
    private String address;

    private String avatar;

    @Size(max = 15)
    private String phoneNumber;

    @NotBlank(message = "Username cannot be empty")
    @Size(max = 25, message = "Maximum number of username is 25")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;
}
