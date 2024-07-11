package com.ursklap.ecommerce.services;

import org.springframework.stereotype.Service;

import com.ursklap.ecommerce.models.Media;
import com.ursklap.ecommerce.repositories.MediaRepository;
import com.ursklap.ecommerce.services.base.BaseService;

@Service
public class MediaService extends BaseService<Media, MediaRepository, Long> {
    
}
