package com.example.demo.controller;


import com.example.demo.model.ReqOfferCreate;
import com.example.demo.model.ResModel;
import com.example.demo.service.OfferService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/offer")
public class OfferController {
    private final OfferService offerService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('OFFER_CREATE')")
    public HttpEntity<?> create(@Valid @RequestBody ReqOfferCreate offerCreate) {
        ResModel resModel = offerService.create(offerCreate);
        return ResponseEntity.ok(resModel);
    }

    @GetMapping("userOffers")
    @PreAuthorize("hasAuthority('OFFER_READ')")
    public HttpEntity<?> list() {
        ResModel resModel = offerService.getUserOffers();
        return ResponseEntity.ok(resModel);
    }



    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('OFFER_DELETE')")
    public HttpEntity<?> getOne(@NotNull(message = "id not be null!") @PathVariable(name = "id") Long id) {
        ResModel delete = offerService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
