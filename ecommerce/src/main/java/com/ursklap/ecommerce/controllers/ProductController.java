package com.ursklap.ecommerce.controllers;

import java.util.List;
import java.util.random.RandomGenerator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ursklap.ecommerce.dto.ProductDto;
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
    public ResponseEntity<ResponseDto<Product>> save(@RequestBody ProductDto request) {
        Product product = this.productService.create(
                Product.builder()
                        .name(request.getName())
                        .description(request.getDescription())
                        .code(request.getCode())
                        .price(request.getPrice())
                        .stock(request.getStock())
                        .isInStock(request.getStock() > 1)
                        .discountedPrice(request.getDiscountedPrice()).build());

        // TODO : Create utils class to simplify this repetitive code
        ResponseDto<Product> response = new ResponseDto<Product>();
        response.setData(product);
        response.setStatus(201);
        response.setMessage("Successfully add product");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseDto<String>> saveBulk(@RequestBody List<ProductDto> request) {
        this.productService.saveBulk(request);
        ResponseDto<String> response = new ResponseDto<String>();
        response.setData("Success");
        response.setStatus(201);
        response.setMessage("Successfully add product");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ResponseDto<List<ProductResponse>>> findAll() {
        ResponseDto<List<ProductResponse>> response = new ResponseDto<List<ProductResponse>>();
        response.setData(this.productService.findAllProduct());
        response.setStatus(200);
        response.setMessage("Successfully retrieve products");
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
