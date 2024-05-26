package com.example.demo.repository;

import com.example.demo.entity.CarrierRegions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarrierRegionRepository extends JpaRepository<CarrierRegions, Long> {
    @Query("SELECT cr FROM CarrierRegions cr JOIN cr.regions r WHERE r.name = :name")
    List<CarrierRegions> findByRegion(@Param("name") String name);
}
