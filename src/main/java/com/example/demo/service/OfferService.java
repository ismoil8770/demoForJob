package com.example.demo.service;

import com.example.demo.entity.Offer;
import com.example.demo.entity.Places;
import com.example.demo.entity.Product;
import com.example.demo.model.ReqOfferCreate;
import com.example.demo.model.ResModel;
import com.example.demo.repository.OfferRepository;
import com.example.demo.repository.PlacesRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferService{
    private final OfferRepository offerRepository;
    private final PlacesRepository placesRepository;
    private final ProductRepository productRepository;
    private final AuthService authService;

    public ResModel create(ReqOfferCreate offerCreate) {
        boolean exists = offerRepository.existsByOfferCode(offerCreate.getCode());
        if (exists)return new ResModel(Status.EXIST);
        Optional<Places> optionalPlaces = placesRepository.findByName(offerCreate.getPlaceName());
        if (optionalPlaces.isEmpty())return new ResModel(Status.NOT_FOUND);
        Optional<Product> optionalProduct = productRepository.findByProductName(offerCreate.getProductCode());
        if (optionalProduct.isEmpty())return new ResModel(Status.NOT_FOUND);
        Offer offer = new Offer();
        offer.setOfferCode(offerCreate.getCode());
        offer.setPlaces(optionalPlaces.get());
        offer.setProduct(optionalProduct.get());
        offer.setAppUser(authService.getCurrentUser());
        offerRepository.save(offer);
        return new ResModel(Status.OK);
    }

    public ResModel getUserOffers() {
        List<Offer> allByAppUserId = offerRepository.findAllByAppUserId(authService.getCurrentUser().getId());
        return new ResModel(Status.OK,allByAppUserId);
    }

    public ResModel delete(Long id) {
        offerRepository.deleteById(id);
        return new ResModel(Status.OK);
    }
}
