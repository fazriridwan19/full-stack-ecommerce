package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.annotation.CurrentUser;
import com.ursklap.ecommerce.dto.requests.CheckoutRequest;
import com.ursklap.ecommerce.dto.requests.PaymentRequest;
import com.ursklap.ecommerce.dto.responses.OrderResponse;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.models.CustomUserDetails;
import com.ursklap.ecommerce.models.Order;
import com.ursklap.ecommerce.services.OrderService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @GetMapping()
    public ResponseEntity<ResponseDto<List<Order>>> findAll(@Parameter(hidden = true) @CurrentUser CustomUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApiGenerator.generator().generate(this.orderService.findAllByUserId(userDetails.getCredential().getUser().getId()), HttpStatus.OK.value(), "Successfully retrieve orders"));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto<OrderResponse>> findById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApiGenerator.generator().generate(this.orderService.findByIdAsDto(id), HttpStatus.OK.value(), "Successfully retrieve order"));
    }

    @PostMapping("checkout")
    public ResponseEntity<ResponseDto<String>> checkout(@RequestBody CheckoutRequest request, @Parameter(hidden = true) @CurrentUser CustomUserDetails userDetails) {
        this.orderService.checkout(request, userDetails);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseApiGenerator.generator().generate("Success", HttpStatus.CREATED.value(), "Checkout success"));
    }

    @PostMapping("payment")
    public ResponseEntity<ResponseDto<String>> payment(@RequestBody PaymentRequest request) {
        this.orderService.payment(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApiGenerator.generator().generate("Success", HttpStatus.OK.value(), "Successfully pay the order"));
    }

    @PostMapping("confirmation/{orderId}")
    public ResponseEntity<ResponseDto<String>> confirmation(@PathVariable("orderId") Long orderId) {
        this.orderService.confirmation(orderId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApiGenerator.generator().generate("Success", HttpStatus.OK.value(), "Successfully pay the order"));
    }
}
