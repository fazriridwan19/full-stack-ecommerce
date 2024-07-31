package com.ursklap.ecommerce.controllers;

import java.util.List;
import java.util.random.RandomGenerator;

import com.ursklap.ecommerce.utils.ResponseApiGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ursklap.ecommerce.dto.requests.ProductRequest;
import com.ursklap.ecommerce.dto.responses.ProductResponse;
import com.ursklap.ecommerce.dto.responses.ResponseDto;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.services.ProductService;
import com.ursklap.ecommerce.threads.ThreadProduct;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Product>> save(@RequestBody ProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.productService.create(request), 201, "Successfully add product"));
    }

    @PostMapping("bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<String>> saveBulk(@RequestBody List<ProductRequest> request) {
        this.productService.saveBulk(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate("Success", 201, "Successfully add product"));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto<List<ProductResponse>>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.productService.findAllProduct(), 200, "Successfully retrieve all product"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto<ProductResponse>> findById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.productService.findByIdProduct(id), 200, "Successfully retrieve all product"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<Product>> update(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ResponseApiGenerator
                                .generator()
                                .generate(this.productService.update(id, request), 201, "Successfully retrieve all product"));
    }


    @PostMapping("/request/test")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<String>> createBulk() {
        for (int i = 0; i < 1000; i++) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;

            RandomGenerator random = RandomGenerator.of("L32X64MixRandom");

            String name = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            String description = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            String code = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            Integer price = 1000;
            Integer stock = 2;
            Integer discountedPrice = 1000;
            Boolean isInStock = stock >= 1;

            Product product = Product.builder()
                    .name(name)
                    .description(description)
                    .code(code)
                    .price(price)
                    .discountedPrice(discountedPrice)
                    .stock(stock)
                    .isInStock(isInStock)
                    .build();

            // this.productService.create(product);
            ThreadProduct threadProduct = new ThreadProduct(this.productService, product);
            threadProduct.start();
        }
        ResponseDto<String> response = new ResponseDto<String>();
        response.setData("Success");
        response.setStatus(201);
        response.setMessage("Successfully add product");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
