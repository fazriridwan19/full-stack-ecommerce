package com.ursklap.ecommerce.models;

import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cart_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDetail extends BaseEntity {
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;

    @OneToMany(mappedBy = "cartDetail", fetch = FetchType.EAGER)
    private List<Order> orders;
}
