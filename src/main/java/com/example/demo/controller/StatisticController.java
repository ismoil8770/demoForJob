package com.example.demo.controller;

import com.example.demo.model.ResModel;
import com.example.demo.service.StatisticService;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/statistics")
public class StatisticController {
    private final StatisticService statisticService;
    @GetMapping("/numberOfTransactionsPerProduct")
    public HttpEntity<?> numberOfTransactionsPerProduct(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResModel(Status.OK,statisticService.getProductTransactionCounts()));
    }

}
