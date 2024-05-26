package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Region extends BaseEntity {
    @Column(unique = true)
    private String name;
    @ManyToMany
    private List<Places> places;
}
