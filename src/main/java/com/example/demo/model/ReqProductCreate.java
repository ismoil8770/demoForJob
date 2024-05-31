package com.example.demo.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqProductCreate {
    @NotNull(message = "productCode required")
    private String productCode;
    @NotNull(message = "productName required")
    @Size(min = 3, message = "product name must be at least 3 characters")
    private String productName;
    @NotNull(message = "Price required")
    @DecimalMin(value = "0.0",message = "price must be positive")
    private Double productPrice;

}
