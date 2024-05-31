package com.example.demo.controller;

import com.example.demo.model.ReqRoleCreate;
import com.example.demo.model.ReqRoleUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.service.RoleService;
import com.example.demo.utils.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    public HttpEntity<?> create(@Valid @RequestBody ReqRoleCreate roleCreate){

            roleService.create(roleCreate);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResModel(Status.OK));
    }

    @PutMapping("/update")
    public HttpEntity<?> update(@Valid @RequestBody ReqRoleUpdate roleUpdate){
        roleService.update(roleUpdate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@NotNull(message = "Id not be null!") @PathVariable(name = "id") Long id){
        roleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK));
    }
}
