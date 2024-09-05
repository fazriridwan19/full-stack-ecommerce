package com.ursklap.ecommerce.dto.requests;

import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressRequest {
    private Long id;

    @Size(max = 100)
    private String street;

    @Size(max = 50)
    private String city;

    @Size(max = 50)
    private String province;

    private Boolean defaultAddress;

    private Integer code;
}
