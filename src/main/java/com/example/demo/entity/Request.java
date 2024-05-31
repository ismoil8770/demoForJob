package com.example.demo.entity;

import com.example.demo.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "request")
public class Request extends BaseEntity {
    @Column(name = "request")
    private String request;
    @Column(name = "code")
    private String code;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "place_id")
    private Long place_id;
    @ManyToOne
    private AppUser appUser;
}
