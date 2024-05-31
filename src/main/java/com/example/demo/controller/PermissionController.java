package com.example.demo.controller;

import com.example.demo.model.ResModel;
import com.example.demo.repository.PermissionRepository;

import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/permission")
public class PermissionController {

    private final PermissionRepository permissionRepo;

    @GetMapping
    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    public HttpEntity<?> permissions(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK,permissionRepo.findAll()));
    }
}
