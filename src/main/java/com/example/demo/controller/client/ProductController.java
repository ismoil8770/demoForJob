package com.example.demo.controller.client;

import com.example.demo.model.ResModel;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public HttpEntity<?> getProducts(){
        ResModel allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }
    @GetMapping("getUserProducts")
    public HttpEntity<?> getUserProducts(){
        return ResponseEntity.ok("");
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok("");
    }
}
