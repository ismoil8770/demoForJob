package com.example.demo.service;

import com.example.demo.entity.AppUser;
import com.example.demo.model.ReqUserModel;
import com.example.demo.model.ReqUserUpdate;
import com.example.demo.model.ResModel;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.utils.PasswordEncoderEnum;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminUserService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder bcryptPasswordEncoder;
    private final PasswordEncoder scryptPasswordEncoder;

    public ResModel addAdminUser(MultipartFile multipartFile, ReqUserModel reqUserModel) {

        boolean exists = appUserRepository.existsByUsernameOrEmail(reqUserModel.getUsername(), reqUserModel.getEmail());
        if (!exists) {
            PasswordEncoderEnum passwordEncoder = PasswordEncoderEnum.valueOf(reqUserModel.getAlgorithmType());
            AppUser appUser = new AppUser();
            appUser.setEmail(reqUserModel.getEmail());
            appUser.setFirstname(reqUserModel.getFirstname());
            appUser.setLastname(reqUserModel.getLastname());
            appUser.setRole(roleRepository.getReferenceById(reqUserModel.getRoleId()));
            appUser.setStatus(reqUserModel.getStatus());
            appUser.setUsername(reqUserModel.getUsername());
            appUser.setPasswordEncoderEnum(passwordEncoder);
            appUser.setPassword(PasswordEncoderEnum.BCRYPT==passwordEncoder?bcryptPasswordEncoder.encode(reqUserModel.getPassword()):scryptPasswordEncoder.encode(reqUserModel.getPassword()));
            Path path = Paths.get("image", multipartFile.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(path)) {
                os.write(multipartFile.getBytes());
                os.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            appUser.setImagePath(path.toUri().getPath());
            appUserRepository.save(appUser);
            return new ResModel(Status.OK);
        } else {
            return new ResModel(Status.EXIST_USER);
        }
    }


    public ResModel updateUser(MultipartFile file, ReqUserUpdate reqUserModel) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(reqUserModel.getId());
        if (appUserOptional.isPresent()){
            AppUser appUser = appUserOptional.get();
            appUser.setEmail(reqUserModel.getEmail());
            appUser.setUsername(reqUserModel.getUsername());
            appUser.setFirstname(reqUserModel.getFirstname());
            appUser.setLastname(reqUserModel.getLastname());
            appUser.setRole(roleRepository.getReferenceById(reqUserModel.getRoleId()));
            appUser.setStatus(reqUserModel.getStatus());
            PasswordEncoderEnum passwordEncoder = PasswordEncoderEnum.valueOf(reqUserModel.getAlgorithmType());
            appUser.setPassword(PasswordEncoderEnum.BCRYPT==passwordEncoder?bcryptPasswordEncoder.encode(reqUserModel.getPassword()):scryptPasswordEncoder.encode(reqUserModel.getPassword()));
            appUser.setPasswordEncoderEnum(passwordEncoder);
            Path path = Paths.get("image", file.getOriginalFilename());
            try (OutputStream os = Files.newOutputStream(path)) {
                os.write(file.getBytes());
                os.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            appUser.setImagePath(path.toUri().getPath());
            appUserRepository.save(appUser);
            return new ResModel(Status.OK);
        }else {
            return new ResModel(Status.USER_NOT_FOUND);
        }
    }

    public ResModel updateStatus(Long id, Boolean status) {
        Optional<AppUser> appUserOptional = appUserRepository.findById(id);
        if (appUserOptional.isPresent()){
            appUserOptional.get().setStatus(status);
            appUserRepository.save(appUserOptional.get());
            return new ResModel(Status.OK);
        }
        return   new ResModel(Status.USER_NOT_FOUND);
    }
}
