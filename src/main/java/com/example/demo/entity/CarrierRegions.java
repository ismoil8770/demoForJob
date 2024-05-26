package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class CarrierRegions extends BaseEntity {
    @OneToOne
    private AppUser appUser;
    @ManyToMany
    private List<Region> regions;
}
