package com.example.demo.controller;

import com.example.demo.model.ReqUserModel;
import com.example.demo.model.ReqUserUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.service.AdminUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/user")
public class AdminController {
    private final AdminUserService adminUserService;

    public AdminController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    @PostMapping
    public HttpEntity<?> adminAdd(@RequestPart("file") MultipartFile file,@Valid @RequestPart("data") ReqUserModel reqUserModel){
        ResModel resModel = adminUserService.addAdminUser(file, reqUserModel);
        return ResponseEntity.ok(resModel);
    }
    @PreAuthorize("hasAuthority('USER_EDIT')")
    @PutMapping("update")
    public HttpEntity<?> adminUpdate(@RequestParam("file") MultipartFile file,@Valid @RequestParam("data") ReqUserUpdate reqUserModel){
        ResModel resModel = adminUserService.updateUser(file, reqUserModel);
        return ResponseEntity.ok(resModel);
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> adminDelete(@PathVariable Long id){
        return ResponseEntity.ok("ok");
    }
    @PreAuthorize("hasAuthority('USER_EDIT')")
    @PutMapping("{id}")
    public HttpEntity<?> updateAdminStatus(@PathVariable Long id,@RequestParam Boolean status){
        ResModel resModel = adminUserService.updateStatus(id,status);
        return ResponseEntity.ok(resModel);
    }
}
