package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByAppUserId(Long id);
    boolean existsByCode(String code);
    Optional<Product> findByCode(String code);
    Optional<Product> findByProductName(String name);
}
