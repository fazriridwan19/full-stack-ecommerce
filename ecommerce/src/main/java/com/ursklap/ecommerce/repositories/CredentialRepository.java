package com.ursklap.ecommerce.repositories;

import com.ursklap.ecommerce.models.Credential;
import com.ursklap.ecommerce.repositories.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepository extends BaseRepository<Credential, Long> {
    Optional<Credential> findByUsername(String username);
}
