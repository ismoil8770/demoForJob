package com.example.demo.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqUserUpdate {
    @NotNull(message = "id required")
    private Long id;
    @NotBlank(message = "First name required")
    @Pattern(regexp = "^[A-Za-z]{3,}$", message = "Invalid input. First name must be at least 3 characters and contain only alphabetic characters")
    private String firstname;
    @NotBlank(message = "Last name required")
    @Pattern(regexp = "^[A-Za-z]{3,}$", message = "Invalid input. Last name must be at least 3 characters and contain only alphabetic characters")
    private String lastname;
    @NotBlank(message = "user name required")
    @Size(min = 3, message = "User name must be at least 3 characters")
    private String username;
    @NotBlank(message = "user name required")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @Pattern(regexp = "^(?=.*[!#@\\$*]).{8,}$", message = "Password must be at least 8 characters and contain at least one special character (!, #, @, $, *)")
    private String password;
    private String algorithmType;
    private Boolean status;
    @NotNull(message = "Role id not be null!")
    private Long roleId;
}
