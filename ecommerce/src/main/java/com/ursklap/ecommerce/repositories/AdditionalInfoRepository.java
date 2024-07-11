package com.ursklap.ecommerce.repositories;

import org.springframework.stereotype.Repository;

import com.ursklap.ecommerce.models.AdditionalInfo;
import com.ursklap.ecommerce.repositories.base.BaseRepository;

@Repository
public interface AdditionalInfoRepository extends BaseRepository<AdditionalInfo, Long>{
    
}
