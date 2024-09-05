package com.ursklap.ecommerce.dto.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressResponse {
    private Long id;
    private String street;
    private String city;
    private String province;
    private Boolean defaultAddress;
    private Integer code;
}
