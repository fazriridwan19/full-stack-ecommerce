package com.ursklap.ecommerce.models;

import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {
    @Column(length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "cart_detail_id", referencedColumnName = "id", nullable = false)
    private CartDetail cartDetail;

    @ManyToOne
    @JoinColumn(name = "current_status_id", referencedColumnName = "id", nullable = false)
    private Status currentStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderHistory> histories;
}
