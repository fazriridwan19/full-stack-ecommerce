package com.ursklap.ecommerce.dto.responses;

import java.util.List;

import com.ursklap.ecommerce.models.Category;
import com.ursklap.ecommerce.models.Media;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Integer price;
    private Integer discountedPrice;
    private Boolean isInStock;
    private Integer stock;
    private CategoryResponse category;
    private List<Media> listMedia;

    public ProductResponse(Long id, String name, String code, String description, Integer price,
            Integer discountedPrice, Boolean isInStock, Integer stock, CategoryResponse category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
        this.discountedPrice = discountedPrice;
        this.isInStock = isInStock;
        this.stock = stock;
        this.category = category;
    }

}
