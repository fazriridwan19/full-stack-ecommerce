package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.annotation.CurrentUser;
import com.ursklap.ecommerce.dto.requests.AddressRequest;
import com.ursklap.ecommerce.dto.requests.ProfileUpdateRequest;
import com.ursklap.ecommerce.dto.responses.AddressResponse;
import com.ursklap.ecommerce.dto.responses.ProfileResponse;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.services.ProfileService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/profile")
@SecurityRequirement(name = "Authorization")
public class ProfileController {
    private ProfileService profileService;

    @GetMapping("get")
    public ResponseEntity<ResponseDto<ProfileResponse>> getProfile(@CurrentUser CustomUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.profileService.getProfile(userDetails), 200, "Successfully fetch profile"));
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDto<ProfileResponse>> updateProfile(@CurrentUser CustomUserDetails userDetails, @RequestBody ProfileUpdateRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.profileService.updateProfile(userDetails, request), 200, "Successfully update profile"));
    }

    @PostMapping("address/add")
    public ResponseEntity<ResponseDto<AddressResponse>> addAddress(@CurrentUser CustomUserDetails userDetails, @RequestBody AddressRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.profileService.addAddress(userDetails, request), 200, "Successfully add address"));
    }

    @PutMapping("address/update")
    public ResponseEntity<ResponseDto<AddressResponse>> updateAddress(@CurrentUser CustomUserDetails userDetails, @RequestBody AddressRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.profileService.updateAddress(userDetails, request), 200, "Successfully update address"));
    }
}
