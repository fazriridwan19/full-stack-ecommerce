package com.ursklap.ecommerce.repositories.base;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ursklap.ecommerce.models.base.BaseEntity;

@Repository
@Primary
public interface BaseRepository<E extends BaseEntity, T> extends JpaRepository<E, T>, JpaSpecificationExecutor<E> {
    
}
