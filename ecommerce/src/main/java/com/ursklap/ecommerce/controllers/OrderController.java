package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.annotation.CurrentUser;
import com.ursklap.ecommerce.dto.requests.CheckoutRequest;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.models.Order;
import com.ursklap.ecommerce.services.OrderService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("checkout")
    public ResponseEntity<ResponseDto<Order>> checkout(@RequestBody CheckoutRequest request, @Parameter(hidden = true) @CurrentUser CustomUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApiGenerator.generator().generate(this.orderService.checkout(request, userDetails), HttpStatus.CREATED.value(), "Checkout success"));
    }
}
