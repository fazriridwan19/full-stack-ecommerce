package com.ursklap.ecommerce.models;

import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderHistory extends BaseEntity {
    @Column(length = 50)
    private String email;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;
}
