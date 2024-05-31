package com.example.demo.service;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.Product;
import com.example.demo.model.ReqProductCreate;
import com.example.demo.model.ResModel;
import com.example.demo.repository.ProductRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final AuthService authService;
    public ResModel getAllProducts(){
        List<Product> all = productRepository.findAll();
        return new ResModel(Status.OK,all);
    }

    public ResModel getUserProduct(){
        AppUser currentUser = authService.getCurrentUser();
        List<Product> products = productRepository.findAllByAppUserId(currentUser.getId());
        return new ResModel(Status.OK,products);

    }

    public ResModel createProduct(MultipartFile file, ReqProductCreate reqProductCreate) {
        boolean exists = productRepository.existsByCode(reqProductCreate.getProductCode());
        if (exists) return new ResModel(Status.EXIST);
        Path path = Paths.get("image", file.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(path)) {
            os.write(file.getBytes());
            os.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Product product = new Product(reqProductCreate.getProductCode(), reqProductCreate.getProductName(), reqProductCreate.getProductPrice(),path.toUri().getPath(), authService.getCurrentUser());
        productRepository.save(product);
        return new ResModel(Status.OK);
    }
}
