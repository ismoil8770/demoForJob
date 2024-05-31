package com.example.demo.repository;

import com.example.demo.entity.Places;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlacesRepository extends JpaRepository<Places, Long> {
    Optional<Places> findByName(String name);
}
