package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.dto.AuthDTO;
import com.prathamesh.decentralizedHealthcareBackend.entity.Auth;
import com.prathamesh.decentralizedHealthcareBackend.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    @Autowired
    AuthRepository authRepository;

    public Auth signUpAuth(AuthDTO authDTO){

        Auth auth = new Auth();
        auth.setRole(authDTO.getRole());
        auth.setUsername(authDTO.getUsername());
        auth.setPassword(authDTO.getPassword());
        return authRepository.save(auth);

    }
}
