package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqOfferCreate {
    @NotNull(message = "code required")
    private String code;
    @NotNull(message = "product required")
    private String productCode;
    @NotNull(message = "place id required")
    private String placeName;
}
