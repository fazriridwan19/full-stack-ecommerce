package com.ursklap.ecommerce.controllers;

import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.models.Payment;
import com.ursklap.ecommerce.services.PaymentService;
import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<Payment>>> findALl(@RequestParam(value = "type", required = false) String type) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApiGenerator.generator()
                        .generate(this.paymentService.findAll(type), HttpStatus.OK.value(), "Successfully retrieve payments"));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDto<Payment>> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseApiGenerator.generator()
                        .generate(this.paymentService.findById(id, "Payment method is not found"), HttpStatus.OK.value(), "Successfully retrieve payments"));
    }
}
