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
public class ReqRequestUpdate {
    @NotNull()
    private Long id;
    @NotNull(message = "request not be null!")
    private String request;
    @NotNull(message = "code not be null!")
    private String code;
    @NotNull(message = "product not be null!")
    private String productCode;
    @NotNull(message = "place not be null!")
    private Long placeId;
}
