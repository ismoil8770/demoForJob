package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqRequestCreate {
    @NotNull(message = "request required")
    private String request;
    @NotNull(message = "code required")
    private String code;
    @NotNull(message = "product required")
    private String productCode;
    @NotNull(message = "place required")
    private Long placeId;
}
