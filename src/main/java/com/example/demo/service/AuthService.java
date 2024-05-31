package com.example.demo.service;

import com.example.demo.entity.AppUser;
import com.example.demo.exception.MyCustomException;
import com.example.demo.model.ReqAuth;
import com.example.demo.model.ResModel;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.security.JwtTokenUtil;
import com.example.demo.utils.PasswordEncoderEnum;
import com.example.demo.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder bcryptPasswordEncoder;
    private final PasswordEncoder scryptPasswordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AppUser getCurrentUser() {
        AppUser user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof AppUser) {
                user = (AppUser) authentication.getPrincipal();
            }
        }
        return user;
    }

    public ResModel login(ReqAuth reqAuth) {
        Optional<AppUser> optional;
        if (reqAuth.getUsernameOrEmail().contains("@")){
            optional = appUserRepository.findByEmail(reqAuth.getUsernameOrEmail());
        }else {
            optional = appUserRepository.findByUsername(reqAuth.getUsernameOrEmail());
        }
        if (optional.isEmpty()) return new ResModel(Status.USER_NOT_FOUND);
        AppUser appUser = optional.get();
        PasswordEncoderEnum passwordEncoderEnum = appUser.getPasswordEncoderEnum();
        boolean isMatched;
        if (passwordEncoderEnum==PasswordEncoderEnum.BCRYPT){
            isMatched=bcryptPasswordEncoder.matches(reqAuth.getPassword(), appUser.getPassword());
        }else {
            isMatched=scryptPasswordEncoder.matches(reqAuth.getPassword(),appUser.getPassword());
        }
        if (isMatched){
            String token = jwtTokenUtil.generateToken(appUser.getUsername());
            return new ResModel(Status.OK, Map.of("token",token));
        }
        return new ResModel(Status.INCORRECT);
    }
}
