package com.ursklap.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String street;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 50)
    private String province;

    private Integer code;

    private Boolean defaultAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
