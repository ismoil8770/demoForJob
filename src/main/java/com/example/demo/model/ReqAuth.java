package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqAuth {
    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;
}
