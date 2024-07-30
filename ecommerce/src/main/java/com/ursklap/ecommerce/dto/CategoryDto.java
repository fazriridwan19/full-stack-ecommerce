package com.ursklap.ecommerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(allowSetters = true, allowGetters = true)
public class CategoryDto {
    private Long id;

    @NotBlank(message = "Category name could not be empty or null")
    @Size(max = 50)
    private String name;
    private String description;
    private String imageUrl;
}
