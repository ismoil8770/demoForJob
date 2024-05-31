package com.example.demo.controller;


import com.example.demo.model.ReqTransaction;
import com.example.demo.model.ResModel;
import com.example.demo.service.TransactionService;
import com.example.demo.utils.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/create")
    public HttpEntity<?> create(@Valid @RequestBody ReqTransaction reqTransaction){

            transactionService.create(reqTransaction);
            return ResponseEntity.status(HttpStatus.OK).body(new ResModel(Status.OK));

    }

    @PostMapping("/evaluate/{code}/{score}")
    public HttpEntity<?> evaluateTransaction(@PathVariable(name = "code") String transactionCode, @PathVariable(name = "score") int score){
            return ResponseEntity.status(HttpStatus.OK).body(new ResModel(Status.OK,transactionService.evaluateTransaction(transactionCode,score)));

    }

    @GetMapping("/user/{id}")
    public HttpEntity<?> getByUser(@NotNull(message = "user id not be null!") @PathVariable(name = "id") Long id){

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResModel(Status.OK,transactionService.getUserTransactions(id)));

    }
}
