package com.ursklap.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status extends BaseEntity {
    @Column(length = 10)
    private String name;

    @OneToMany(mappedBy = "currentStatus", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Order> orders;

    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<OrderHistory> histories;
}
