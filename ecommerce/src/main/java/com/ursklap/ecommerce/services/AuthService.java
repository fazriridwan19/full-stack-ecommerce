package com.ursklap.ecommerce.services;

import com.ursklap.ecommerce.dto.requests.LoginRequest;
import com.ursklap.ecommerce.dto.requests.RegistrationRequest;
import com.ursklap.ecommerce.models.Cart;
import com.ursklap.ecommerce.models.Credential;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.models.User;
import com.ursklap.ecommerce.repositories.CartRepository;
import com.ursklap.ecommerce.repositories.CredentialRepository;
import com.ursklap.ecommerce.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private ModelMapper modelMapper;
    private UserRepository userRepository;
    private CredentialRepository credentialRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private CustomUserDetailsService customUserDetailsService;
    private JwtService jwtService;
    private CartRepository cartRepository;

    @Transactional
    public void registration(RegistrationRequest request) {
        Credential credential = modelMapper.map(request, Credential.class);
        credential.setPassword(passwordEncoder.encode(request.getPassword()));

        User user = modelMapper.map(request, User.class);
        user.setCredential(credential);

        Cart cart = new Cart();
        cart.setUser(user);

        credentialRepository.save(credential);
        userRepository.save(user);
        cartRepository.save(cart);
    }

    public String login(LoginRequest request) {
        log.info("Logging in ...");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException exception) {
            log.error(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Username or password is not valid");
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());
        return jwtService.generateToken(userDetails);
    }
}
