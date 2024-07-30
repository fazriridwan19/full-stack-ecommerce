package com.ursklap.ecommerce.dto;

import java.util.List;

import com.ursklap.ecommerce.models.Media;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    @Size(max = 50)
    private String name;

    @NotBlank(message = "Product code cannot be empty")
    private String code;

    @NotBlank(message = "Product description cannot be empty")
    private String description;

    @NotBlank(message = "Product price cannot be empty")
    private Integer price;

    private Integer discountedPrice;

    @NotBlank(message = "Product stock cannot be empty")
    private Integer stock;

    private Long categoryId;

    private List<Media> listMedia;
}
