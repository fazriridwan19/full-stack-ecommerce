package com.ursklap.ecommerce.utils;

import com.ursklap.ecommerce.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidateProduct {
    public static void validateAvailability(Product product, Integer quantity) {
        if (!product.getIsInStock()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Cannot process product with id " + product.getId() + " product out of stock");
        }
        if (quantity > product.getStock()) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Cannot process cart detail with id " + product.getId() + " maximum quantity product is " + product.getStock());
        }
    }
}
