package com.example.demo.controller;

import com.example.demo.model.ReqAuth;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public HttpEntity<?> login(@Valid @RequestBody ReqAuth reqAuth){
        authService.login(reqAuth);

        return ResponseEntity.ok("");
    }
}
