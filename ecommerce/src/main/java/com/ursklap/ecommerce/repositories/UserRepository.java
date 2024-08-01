package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.User;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
