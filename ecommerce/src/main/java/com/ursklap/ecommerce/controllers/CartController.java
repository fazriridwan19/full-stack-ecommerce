package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.annotation.CurrentUser;
import com.ursklap.ecommerce.dto.requests.CartRequest;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.services.CartService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private CartService cartService;

    @PostMapping("add-product")
    public ResponseEntity<ResponseDto<String>> addProduct(@RequestBody CartRequest request, @CurrentUser CustomUserDetails userDetails) {
        this.cartService.addProduct(request, userDetails);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate("Successfully add product to cart", HttpStatus.CREATED.value(), "Successfully add product to cart"));
    }
}
