package com.ursklap.ecommerce.threads;

import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ThreadProduct extends Thread {
    private ProductService productService;
    private Product product;

    @Override
    public void run() {
        this.productService.create(product);
    }

}
