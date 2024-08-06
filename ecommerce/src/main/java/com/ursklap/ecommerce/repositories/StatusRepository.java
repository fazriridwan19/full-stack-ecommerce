package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.Status;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends BaseRepository<Status, Long> {
}
