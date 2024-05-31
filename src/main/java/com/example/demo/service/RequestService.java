package com.example.demo.service;


import com.example.demo.entity.Places;
import com.example.demo.entity.Product;
import com.example.demo.entity.Request;
import com.example.demo.exception.MyCustomException;
import com.example.demo.model.ReqRequestCreate;
import com.example.demo.model.ReqRequestUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.PlacesRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RequestRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final PlacesRepository placeRepo;

    private final ProductRepository productRepo;
    private final AuthService authService;

    public ResModel create(ReqRequestCreate requestCreate)  {
        Optional<Request> optionalRequest = requestRepository.findByCode(requestCreate.getCode());
        if (optionalRequest.isPresent()){
            return new ResModel(Status.EXIST);
        }
        return requestToSave(requestCreate.getProductCode(), requestCreate.getPlaceId(), new Request(), requestCreate.getRequest(), requestCreate.getCode());
    }

    public ResModel update(ReqRequestUpdate requestUpdate) {
        Optional<Request> optionalRequest = requestRepository.findById(requestUpdate.getId());
        if (optionalRequest.isEmpty()){
            throw new MyCustomException(Status.NOT_FOUND.getMessage());
        }
       return requestToSave(requestUpdate.getProductCode(), requestUpdate.getPlaceId(), new Request(), requestUpdate.getRequest(), requestUpdate.getCode());
    }

    private ResModel requestToSave(String productCode, Long placeId, Request request, String request1, String code) {
        Product product = productRepo.findByCode(productCode).orElseThrow(() -> new MyCustomException(Status.NOT_FOUND.getMessage()));
        Places place = placeRepo.findById(placeId).orElseThrow(() ->new MyCustomException(Status.NOT_FOUND.getMessage()));
        request.setRequest(request1);
        request.setCode(code);
        request.setPlace_id(place.getId());
        request.setAppUser(authService.getCurrentUser());
        request.setProductId(product.getId());
        requestRepository.save(request);
        return new ResModel(Status.OK);
    }

    public ResModel list(){
        return new ResModel(Status.OK,requestRepository.findAll());
    }


    public ResModel findOne(Long id) {
        Optional<Request> byId = requestRepository.findById(id);
        return new ResModel(Status.OK,byId.get());
    }
}
