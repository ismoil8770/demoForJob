package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Product extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String code;
    @Column(unique = true,nullable = false)
    private String productName;
    @Column(nullable = false)
    private Double productPrice;
    private String imagePath;
    @ManyToOne
    private AppUser appUser;
}
