package com.example.demo.controller;

import com.example.demo.model.ReqRegionCreate;
import com.example.demo.model.ReqRegionUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.service.RegionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/region")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PreAuthorize("hasAuthority('REGION_CREATE')")
    @PostMapping
    public HttpEntity<?> addRegion(@Valid @RequestBody ReqRegionCreate reqRegionCreate) {
        ResModel resModel = regionService.create(reqRegionCreate);
        return ResponseEntity.ok(resModel);
    }

    @PreAuthorize("hasAuthority('REGION_EDITE')")
    @PutMapping("/update")
    public HttpEntity<?> update(@Valid @RequestBody ReqRegionUpdate regionUpdate) {
        ResModel update = regionService.update(regionUpdate);
        return ResponseEntity.ok(update);
    }
    @PreAuthorize("hasAnyAuthority('REGION_DELETE')")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteRegion(@NotNull(message = "Id not be null!") @PathVariable Long id){
        ResModel delete = regionService.delete(id);
        return ResponseEntity.ok(delete);
    }
    @GetMapping("getRegionAndPlace")
    public HttpEntity<?> getRegionAndPlaces(){
        ResModel resModel = regionService.getRegionAndPlaces();
        return ResponseEntity.ok(resModel);
    }

}
