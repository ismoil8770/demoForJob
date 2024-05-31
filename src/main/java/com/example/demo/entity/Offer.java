package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Offer extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String offerCode;
    @ManyToOne
    private Places places;
    @ManyToOne
    private Product product;
    @ManyToOne
    private AppUser appUser;
}
