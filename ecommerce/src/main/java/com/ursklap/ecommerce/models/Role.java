package com.ursklap.ecommerce.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Credential> credentials;
}
