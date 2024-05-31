package com.example.demo.controller;

import com.example.demo.dto.CarrierRegionDto;
import com.example.demo.model.ResModel;
import com.example.demo.service.CarrierService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/carrier")
public class CarrierController {
    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CarrierRegionDto carrierRegionDto){
        ResModel resModel = carrierService.addRegionsToCarriers(carrierRegionDto);
        return ResponseEntity.ok(resModel);
    }

    @GetMapping("getCarriersForRegion")
    public HttpEntity<?> getCarriersForRegion(@RequestParam String regionName){
        ResModel resModel = carrierService.getCarriersByRegion(regionName);
        return ResponseEntity.ok(resModel);

    }
}
