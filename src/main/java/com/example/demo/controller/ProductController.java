package com.example.demo.controller;

import com.example.demo.model.ReqProductCreate;
import com.example.demo.model.ResModel;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("client/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public HttpEntity<?> getProducts(){
        ResModel allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }
    @GetMapping("getUserProducts")
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public HttpEntity<?> getUserProducts(){
        ResModel userProduct = productService.getUserProduct();
        return ResponseEntity.ok(userProduct);
    }
    @PostMapping
    public HttpEntity<?> addProduct(@RequestPart MultipartFile file, @Valid @RequestPart ReqProductCreate reqProductCreate){
        ResModel resModel = productService.createProduct(file, reqProductCreate);
        return ResponseEntity.ok(resModel);

    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id){

        return ResponseEntity.ok("");
    }
}
