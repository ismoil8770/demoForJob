package com.example.demo.service;

import com.example.demo.entity.AppUser;
import com.example.demo.model.ResModel;
import com.example.demo.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final AppUserRepository appUserRepository;

    public ResModel addAdminUser(AppUser appUser){

    }


}
