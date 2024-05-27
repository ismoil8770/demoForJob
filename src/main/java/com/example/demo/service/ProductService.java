package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.model.ResModel;
import com.example.demo.repository.ProductRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ResModel getAllProducts(){
        List<Product> all = productRepository.findAll();
        return new ResModel(Status.OK,all);
    }

    public ResModel getUserProduct(){

    }
}
