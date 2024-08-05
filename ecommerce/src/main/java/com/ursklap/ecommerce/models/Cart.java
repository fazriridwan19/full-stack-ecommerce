package com.ursklap.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart extends BaseEntity {
    private Integer totalQuantity;

    private Integer totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartDetail> cartDetails;
}
