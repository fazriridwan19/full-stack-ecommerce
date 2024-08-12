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
    @Column(length = 50)
    private String email;

    @ManyToOne
    @JoinColumn(name = "cart_detail_id", referencedColumnName = "id", nullable = false)
    private CartDetail cartDetail;

    @ManyToOne
    @JoinColumn(name = "current_status_id", referencedColumnName = "id", nullable = false)
    private Status currentStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OrderHistory> histories;
}
