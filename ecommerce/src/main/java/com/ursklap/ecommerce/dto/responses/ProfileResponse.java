package com.ursklap.ecommerce.dto.responses;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProfileResponse {
    private String id;
    private String name;
    private String email;
    private String avatar;
    private String phoneNumber;
    private List<AddressResponse> addresses;
}
