package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqPlaceCreate {
    @NotNull(message = "Name not be null!")
    @Size(min = 3,message = "Name must be at least 3 characters")
    private String name;
}
