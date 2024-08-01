package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.dto.requests.LoginRequest;
import com.ursklap.ecommerce.dto.requests.RegistrationRequest;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.services.AuthService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("registration")
    public ResponseEntity<ResponseDto<String>> registration(@RequestBody RegistrationRequest request) {
        this.authService.registration(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate("Success", HttpStatus.CREATED.value(), "Successfully register user"));
    }

    @PostMapping("login")
    public ResponseEntity<ResponseDto<String>> login(@RequestBody LoginRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.authService.login(request), HttpStatus.OK.value(), "Successfully logged in user"));
    }
}
