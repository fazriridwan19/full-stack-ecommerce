package com.ursklap.ecommerce.models;

import java.util.List;

import com.ursklap.ecommerce.models.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Builder
public class Category extends BaseEntity {
    @Column(nullable = false, length = 50)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String description;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String imageUrl;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
