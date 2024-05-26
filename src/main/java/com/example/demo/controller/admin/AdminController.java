package com.example.demo.controller.admin;

import com.example.demo.entity.AppUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/user")
public class AdminController {
    @PostMapping
    public HttpEntity<?> adminAdd(@RequestParam("file") MultipartFile file, @RequestParam("data") AppUser appUser){
        return ResponseEntity.ok("ok");
    }
    @PutMapping
    public HttpEntity<?> adminUpdate(){
        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> adminDelete(@PathVariable Long id){
        return ResponseEntity.ok("ok");
    }
    @PutMapping("{id}")
    public HttpEntity<?> updateAdminStatus(@PathVariable Long id,@RequestParam Boolean status){
        return ResponseEntity.ok("ok");
    }
}
