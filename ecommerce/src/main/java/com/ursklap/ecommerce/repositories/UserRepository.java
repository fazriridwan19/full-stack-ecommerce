package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.User;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN u.credential as c WHERE c.username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);
}
