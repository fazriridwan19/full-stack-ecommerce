package com.ursklap.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order extends BaseEntity {
    @Column(unique = true)
    private String code;

    @Column(nullable = false)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String paymentAccount;

    @Column(length = 20)
    private String orderDate;

    @Column(length = 10)
    private String orderTime;

    @Column(length = 10)
    private String paymentDate;

    @Column(length = 10)
    private String paymentTime;

    @ManyToOne
    @JoinColumn(name = "current_status_id", referencedColumnName = "id", nullable = false)
    private Status currentStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderItem> items;


    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OrderHistory> histories;
}
