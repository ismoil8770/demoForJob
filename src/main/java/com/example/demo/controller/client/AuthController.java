package com.example.demo.controller.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client/auth")
public class AuthController {

    @PostMapping("register")
    public HttpEntity<?> register(){
        return ResponseEntity.ok("");
    }
    @PostMapping("login")
    public HttpEntity<?> login(){
        return ResponseEntity.ok("");
    }
}
