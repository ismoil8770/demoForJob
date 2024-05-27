package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Transaction extends BaseEntity {
    @OneToOne
    private Request request;
    @OneToOne
    private Offer offer;
}
