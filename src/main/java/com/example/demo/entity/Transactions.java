package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transactions extends BaseEntity {
    @Column(name = "code")
    private String code;
    @ManyToOne
    private AppUser carrier;
    @ManyToOne
    private AppUser currentUser;
    @ManyToOne
    private Request request;
    @ManyToOne
    private Offer offer;
    @Column(name = "score")
    private Integer score;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Places> places;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Product> products;

}
