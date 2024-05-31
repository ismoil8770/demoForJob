package com.example.demo.service;


import com.example.demo.entity.AppUser;
import com.example.demo.entity.Offer;
import com.example.demo.entity.Request;
import com.example.demo.entity.Transactions;
import com.example.demo.exception.MyCustomException;
import com.example.demo.model.ReqTransaction;
import com.example.demo.repository.*;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepo;
    private final RequestRepository requestRepo;
    private final OfferRepository offerRepo;
    private final AppUserRepository appUserRepository;
    private final AuthService authService;

    private final PlacesRepository placeRepo;
    public void create(ReqTransaction reqTransaction) {
        Request request = requestRepo.findByCode(reqTransaction.getRequestCode()).orElseThrow(() -> new MyCustomException(Status.NOT_FOUND.getMessage()));
        Offer offer = offerRepo.findByOfferCode(reqTransaction.getOfferCode()).orElseThrow(() -> new MyCustomException(Status.NOT_FOUND.getMessage()));
        AppUser carrier = appUserRepository.findByUsername(reqTransaction.getCarrierName()).orElseThrow(() -> new MyCustomException(Status.NOT_FOUND.getMessage()));
        transactionRepo.findByRequestCodeAndOfferOfferCode(reqTransaction.getRequestCode(), reqTransaction.getOfferCode()).orElseThrow(() -> new MyCustomException(Status.EXIST.getMessage()));
        Transactions transactions = new Transactions();
        transactions.setCode(reqTransaction.getTransactionCode());
        transactions.setOffer(offer);
        transactions.setRequest(request);
        transactions.setCarrier(carrier);
        transactions.setCurrentUser(authService.getCurrentUser());
        transactions.setPlaces(placeRepo.findAllById(reqTransaction.getPlaces()));
        transactionRepo.save(transactions);
    }

    public Object getUserTransactions(Long userId)  {
        Optional<Transactions> transactionsOptional = transactionRepo.findByCurrentUserId(userId);
        if (transactionsOptional.isEmpty()){
            throw new MyCustomException(Status.NOT_FOUND.getMessage());
        }
        return transactionsOptional.get();
    }

    public Boolean evaluateTransaction(String transactionCode,int score)   {
        Optional<Transactions> optionalTransactions = transactionRepo.findByCode(transactionCode);
        if (optionalTransactions.isEmpty()){
            throw new MyCustomException(Status.NOT_FOUND.getMessage());
        }
        Transactions transactions = optionalTransactions.get();
        transactions.setScore(score);
        transactionRepo.save(transactions);
        return score > 1;
    }
}
