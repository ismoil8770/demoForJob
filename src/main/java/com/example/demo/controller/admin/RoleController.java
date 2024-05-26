package com.example.demo.controller.admin;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RoleController {
    @PostMapping
    public HttpEntity<?> addRole(){
        return ResponseEntity.ok("");
    }
    @PutMapping
    public HttpEntity<?> updateRole(){
        return ResponseEntity.ok("");
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteRole(@PathVariable Integer id){
        return ResponseEntity.ok("");
    }
    @GetMapping("permissions")
    public HttpEntity<?> getPermissionList(){
        return ResponseEntity.ok("");
    }
}
