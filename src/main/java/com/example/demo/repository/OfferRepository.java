package com.example.demo.repository;

import com.example.demo.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends JpaRepository<Offer,Long> {
    boolean existsByOfferCode(String code);
    List<Offer> findAllByAppUserId(Long id);
    Optional<Offer> findByOfferCode(String code);
}
