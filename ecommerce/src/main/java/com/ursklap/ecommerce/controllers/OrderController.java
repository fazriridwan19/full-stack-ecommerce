package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.dto.CheckoutRequest;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.services.OrderService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("checkout")
    public ResponseEntity<ResponseDto<String>> checkout(@RequestBody List<CheckoutRequest> requests) {
        ;
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApiGenerator.generator().generate("Success", HttpStatus.CREATED.value(), this.orderService.checkoutBulk(requests)));
    }
}
