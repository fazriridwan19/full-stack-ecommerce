package com.ursklap.ecommerce.models;

import com.ursklap.ecommerce.models.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "credential")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Credential extends BaseEntity {
    @Column(unique = true, length = 25, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Boolean isEnable = true;

    @OneToOne(mappedBy = "credential")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "credential_role",
            joinColumns = @JoinColumn(name = "credential_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
