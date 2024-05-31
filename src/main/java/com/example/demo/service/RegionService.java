package com.example.demo.service;

import com.example.demo.entity.Region;
import com.example.demo.model.ReqRegionCreate;
import com.example.demo.model.ReqRegionUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.repository.PlacesRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final PlacesRepository placesRepository;
    private final AuthService authService;

    public ResModel create(ReqRegionCreate reqRegionCreate) {
        Optional<Region> regionOptional = regionRepository.findByName(reqRegionCreate.getName());
        if (regionOptional.isPresent()) return new ResModel(Status.EXIST);
        Region region = new Region();
        region.setName(reqRegionCreate.getName());
        region.setPlaces(placesRepository.findAllById(reqRegionCreate.getPlaceIds()));
        regionRepository.save(region);
        return new ResModel(Status.OK);
    }

    public ResModel update(ReqRegionUpdate regionUpdate) {
        Optional<Region> optional = regionRepository.findById(regionUpdate.getId());
        if (optional.isEmpty()) {
            return new ResModel(Status.NOT_FOUND);
        }
        Region region = optional.get();
        region.setName(regionUpdate.getName());
        region.setPlaces(placesRepository.findAllById(regionUpdate.getPlaceIds()));
        regionRepository.save(region);
        return new ResModel(Status.OK);
    }

    public ResModel delete(Long id) {
        boolean exist = regionRepository.existsById(id);
        if (exist) {
            regionRepository.deleteById(id);
            return new ResModel(Status.OK);
        }
        return new ResModel(Status.NOT_FOUND);
    }

    public ResModel getRegionAndPlaces() {
        List<Region> all = regionRepository.findAll();
        all.sort(Comparator.comparing(Region::getName));
        return new ResModel(Status.OK,all);
    }
}
