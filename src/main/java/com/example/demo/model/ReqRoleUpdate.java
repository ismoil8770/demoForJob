package com.example.demo.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqRoleUpdate {
    @NotNull(message = "Id not be null!")
    private Long id;
    @NotNull(message = "Name not be null!")
    private String name;
    private Set<Long> permissions;
    private String description;
}
