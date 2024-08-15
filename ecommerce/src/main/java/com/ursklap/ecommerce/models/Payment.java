package com.ursklap.ecommerce.models;

import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class Payment extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 100, unique = true)
    private String account;

    @OneToMany(mappedBy = "payment", fetch = FetchType.EAGER)
    private List<Order> orders;
}
