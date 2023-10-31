package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Admin;
import com.prathamesh.decentralizedHealthcareBackend.entity.LoginModel;
import com.prathamesh.decentralizedHealthcareBackend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceImpl{

    @Autowired
    AdminRepository adminRepository;
    @Override
    public Admin signUpAdmin(Admin admin) {

        String tempPass = admin.getPassword();

        admin.setPassword(new BCryptPasswordEncoder().encode(tempPass));

        return adminRepository.save(admin);
    }

}
