package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Places extends BaseEntity {
    private String name;
}
