package com.ursklap.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.ursklap.ecommerce.models.Media;
import com.ursklap.ecommerce.repositories.base.BaseRepository;

@Repository
public interface MediaRepository extends BaseRepository<Media, Long>{
    
}
