package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.models.Credential;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.repositories.CredentialRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = credentialRepository
                .findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new CustomUserDetails(credential);
    }
}
