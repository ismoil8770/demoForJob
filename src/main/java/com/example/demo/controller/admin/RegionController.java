package com.example.demo.controller.admin;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/region")
public class RegionController {

    @PostMapping
    public HttpEntity<?> addRegion(){
        return ResponseEntity.ok("");
    }

}
