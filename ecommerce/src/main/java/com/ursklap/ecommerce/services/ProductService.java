package com.ursklap.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ursklap.ecommerce.dto.requests.ProductRequest;
import com.ursklap.ecommerce.dto.responses.ProductResponse;
import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.ProductRepository;
import com.ursklap.ecommerce.services.base.BaseService;
import com.ursklap.ecommerce.threads.ThreadProduct;

import lombok.AllArgsConstructor;
import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;

@Service
@AllArgsConstructor
public class ProductService extends BaseService<Product, ProductRepository, Long> {
    private ProductRepository productRepository;
    private CategoryService categoryService;

    public Product create(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .code(request.getCode())
                .price(request.getPrice())
                .stock(request.getStock())
                .isInStock(request.getStock() > 0)
                .discountedPrice(request.getDiscountedPrice()).build();
        if (request.getCategoryId() != null) {
            Category category = this.categoryService.findById(request.getCategoryId());
            product.setCategory(category);
        }
        return super.create(product);
    }

    public Product update(Long id, ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .code(request.getCode())
                .price(request.getPrice())
                .stock(request.getStock())
                .isInStock(request.getStock() > 0)
                .discountedPrice(request.getDiscountedPrice()).build();
        return super.update(id, product);
    }

    public void saveBulk(List<ProductRequest> productRequests) {
        for (ProductRequest dto : productRequests) {
            Product product = Product.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .code(dto.getCode())
                    .price(dto.getPrice())
                    .stock(dto.getStock())
                    .isInStock(dto.getStock() > 0)
                    .discountedPrice(dto.getDiscountedPrice()).build();
            if (dto.getCategoryId() != null) {
                Category category = this.categoryService.findById(dto.getCategoryId());
                product.setCategory(category);
            }
            ThreadProduct threadProduct = new ThreadProduct(this, product);
            threadProduct.start();
        }
    }

    public List<ProductResponse> findAllProduct() {
        return this.productRepository.findAllProductDto();
    }

    public ProductResponse findByIdProduct(Long id) {
        Optional<ProductResponse> response = this.productRepository.findByIdProductDto(id);
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + id + " is not found");
        }
        return response.get();
    }
}
