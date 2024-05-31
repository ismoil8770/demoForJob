package com.example.demo.controller;


import com.example.demo.model.ReqRequestCreate;
import com.example.demo.model.ReqRequestUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.service.RequestService;
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
@RequestMapping("/api/request")
public class RequestController {
    private final RequestService requestService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('REQUEST_CREATE')")
    public HttpEntity<?> create(@Valid @RequestBody ReqRequestCreate requestCreate) {


        return ResponseEntity.ok(requestService.create(requestCreate));

    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('REQUEST_UPDATE')")
    public HttpEntity<?> update(@Valid @RequestBody ReqRequestUpdate reqRequestUpdate) {

        ResModel resModel = requestService.update(reqRequestUpdate);
        return ResponseEntity.ok(resModel);

    }

    @GetMapping
    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public HttpEntity<?> list() {
        return ResponseEntity.ok(requestService.list());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('REQUEST_READ')")
    public HttpEntity<?> getOne(@NotNull(message = "id not be null!") @PathVariable(name = "id") Long id) {

        return ResponseEntity.ok(requestService.findOne(id));

    }
}
