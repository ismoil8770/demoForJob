package com.example.demo.controller;

import com.example.demo.model.ReqPlaceCreate;
import com.example.demo.model.ReqPlaceUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.service.PlaceService;
import com.example.demo.utils.Status;
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
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;

    @PreAuthorize("hasAuthority('PLACE_CREATE')")
    @PostMapping("/create")
    public HttpEntity<?> create(@Valid @RequestBody ReqPlaceCreate placeCreate) {
        placeService.create(placeCreate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK));

    }

    @PreAuthorize("hasAuthority('PLACE_EDITE')")
    @PutMapping("/update")
    public HttpEntity<?> update(@Valid @RequestBody ReqPlaceUpdate placeUpdate) {
        placeService.update(placeUpdate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK));
    }

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping
    public HttpEntity<?> list() {
        Object list = placeService.list();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK, list));
    }

    @PreAuthorize("hasAuthority('PLACE_READ')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@NotNull(message = "id not be null!") @PathVariable(name = "id") Long id) {
        Object one = placeService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK, one));
    }

    @PreAuthorize("hasAuthority('PLACE_DELETE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@NotNull(message = "Id not be null!") @PathVariable(name = "id") Long id) {
        
        placeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK));
    }
}
