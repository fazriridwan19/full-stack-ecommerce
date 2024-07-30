package com.ursklap.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ursklap.ecommerce.dto.ProductDto;
import com.ursklap.ecommerce.dto.responses.ProductResponse;
import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.models.Product;
import com.ursklap.ecommerce.repositories.ProductRepository;
import com.ursklap.ecommerce.services.base.BaseService;
import com.ursklap.ecommerce.threads.ThreadProduct;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService extends BaseService<Product, ProductRepository, Long> {
    private ProductRepository productRepository;
    private CategoryService categoryService;

    public void saveBulk(List<ProductDto> productDtos) {
        for (ProductDto dto : productDtos) {
            Product product = Product.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .code(dto.getCode())
                    .price(dto.getPrice())
                    .stock(dto.getStock())
                    .isInStock(dto.getStock() >= 1)
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

}
