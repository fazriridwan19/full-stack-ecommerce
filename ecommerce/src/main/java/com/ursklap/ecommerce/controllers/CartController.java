package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.annotation.CurrentUser;
import com.ursklap.ecommerce.dto.requests.CartDetailUpdateRequest;
import com.ursklap.ecommerce.dto.requests.CartRequest;
import com.ursklap.ecommerce.dto.responses.CartResponse;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.services.CartService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
@SecurityRequirement(name = "Authorization")
public class CartController {
    private CartService cartService;

    @PostMapping("add-product")
    public ResponseEntity<ResponseDto<String>> addProduct(@RequestBody CartRequest request, @Parameter(hidden = true) @CurrentUser CustomUserDetails userDetails) {
        this.cartService.addProduct(request, userDetails);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate("Successfully add product to cart", HttpStatus.CREATED.value(), "Successfully add product to cart"));
    }

    @GetMapping("items")
    public ResponseEntity<ResponseDto<List<CartResponse>>> findCartDetailsByCurrentUserCart(@Parameter(hidden = true) @CurrentUser CustomUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.cartService.findCartDetailsByCurrentUserCart(userDetails), HttpStatus.OK.value(), "Successfully retrieve product from cart"));
    }

    @GetMapping("cart-detail/{id}")
    public ResponseEntity<ResponseDto<CartResponse>> findCartDetailById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.cartService.findCartDetailByIdAsCartResponse(id), HttpStatus.OK.value(), "Successfully retrieve product from cart"));
    }

    @PutMapping("cart-detail/update")
    public ResponseEntity<ResponseDto<String>> updateCartDetail(@RequestBody CartDetailUpdateRequest request) {
        this.cartService.updateCartDetail(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate("Success", HttpStatus.OK.value(), "Successfully update product from cart"));
    }

    @DeleteMapping("cart-detail/remove/{id}")
    public ResponseEntity<ResponseDto<String>> removeCartDetail(@PathVariable("id") Long id) {
        this.cartService.removeCartDetail(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate("Success", HttpStatus.OK.value(), "Successfully remove product from cart"));
    }
}
