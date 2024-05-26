package com.example.demo.service;

import com.example.demo.dto.CarrierRegionDto;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.CarrierRegions;
import com.example.demo.entity.Region;
import com.example.demo.model.ResModel;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.CarrierRegionRepository;
import com.example.demo.repository.RegionRepository;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrierService {
    private final AppUserRepository appUserRepository;
    private final RegionRepository regionRepository;
    private final CarrierRegionRepository carrierRegionRepository;
    public ResModel getRegionsByCarrier(CarrierRegionDto carrierRegionDto) {
        return null;
    }

    public ResModel addRegionsToCarriers(CarrierRegionDto carrierRegionDto) {
        Optional<AppUser> username = appUserRepository.findByUsername(carrierRegionDto.getUsername());
        if (username.isPresent()){
            List<Region> regionList = regionRepository.findAllByNameIn(carrierRegionDto.getRegionNames());
            CarrierRegions carrierRegions = new CarrierRegions();
            carrierRegions.setRegions(regionList);
            carrierRegions.setAppUser(username.get());
            regionList.sort(Comparator.comparing(Region::getName));
            return new ResModel(Status.OK,regionList);
        }else {
            return new ResModel(Status.USER_NOT_FOUND);
        }
    }

    public ResModel getCarriersByRegion(String regionName) {
        List<CarrierRegions> carrierRegions = carrierRegionRepository.findByRegion(regionName);
        carrierRegions.sort(Comparator.comparing(o -> o.getAppUser().getFirstname()));
        List<AppUser> collect = carrierRegions.stream().map(CarrierRegions::getAppUser).toList();
        return new ResModel(Status.OK,collect);
    }
}
