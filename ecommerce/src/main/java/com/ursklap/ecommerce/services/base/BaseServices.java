package com.ursklap.ecommerce.services.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ursklap.ecommerce.models.base.BaseEntity;
import com.ursklap.ecommerce.repositories.base.BaseRepository;

@Service
@Primary
public class BaseServices<E extends BaseEntity, T> {
    @Autowired
    private BaseRepository<E, T> baseRepository;

    public List<E> findAll() {
        return this.baseRepository.findAll();
    }

    public E findById(T id) {
        return this.baseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity is not found"));
    } 

    public E findById(T id, String errorMessage) {
        return this.baseRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage));
    } 

    public E create(E entity) {
        return this.baseRepository.save(entity);
    }

    public E update(T id, E entity) {
        this.findById(id);
        entity.setId((Long) id);
        return this.baseRepository.save(entity);
    }

    public void delete(T id) {
        E entityDb = this.findById(id);
        this.baseRepository.delete(entityDb);
    }
}
