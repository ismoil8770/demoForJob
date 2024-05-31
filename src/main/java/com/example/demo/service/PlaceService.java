package com.example.demo.service;


import com.example.demo.entity.Places;
import com.example.demo.exception.MyCustomException;
import com.example.demo.model.ReqPlaceCreate;
import com.example.demo.model.ReqPlaceUpdate;
import com.example.demo.repository.PlacesRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlacesRepository placeRepo;

    public void create(ReqPlaceCreate placeCreate)  {
        Optional<Places> optional = placeRepo.findByName(placeCreate.getName());
        if (optional.isPresent()) {
            throw new MyCustomException(Status.EXIST.getMessage());
        }
        Places place = new Places();
        placeToSave(place, placeCreate.getName());
    }

    public void update(ReqPlaceUpdate placeUpdate) {
        Optional<Places> optional = placeRepo.findById(placeUpdate.getId());
        if (optional.isEmpty()) {
            throw new MyCustomException(Status.NOT_FOUND.getMessage());
        }
        Places place = optional.get();
        placeToSave(place, placeUpdate.getName());
    }

    public void delete(Long id)  {
        Optional<Places> optional = placeRepo.findById(id);
        if (optional.isEmpty()) {
            throw new MyCustomException(Status.NOT_FOUND.getMessage());        }
        placeRepo.delete(optional.get());
    }

    private void placeToSave(Places place, String name) {
        place.setName(name);
        placeRepo.save(place);
    }

    public Object list() {
        return placeRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Object getOne(Long id)  {
        Optional<Places> optional = placeRepo.findById(id);
        if (optional.isEmpty()){
            throw new MyCustomException(Status.NOT_FOUND.getMessage());        }
        return optional.get();
    }
}
