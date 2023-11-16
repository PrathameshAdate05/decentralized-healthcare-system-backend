package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Admin;
import com.prathamesh.decentralizedHealthcareBackend.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements AdminServiceImpl{

    Logger logger = LoggerFactory.getLogger(AdminService.class);
    @Autowired
    AdminRepository adminRepository;



    @Override
    public Admin signUpAdmin(Admin admin) {

        String tempPass = admin.getPassword();

        admin.setPassword(new BCryptPasswordEncoder().encode(tempPass));

        return adminRepository.save(admin);
    }

    @Override
    public boolean loginAdmin(String email, String pass) {
       Admin admin = adminRepository.findByEmail(email);

       if (admin == null){
           return false;
       }

       if (!new BCryptPasswordEncoder().matches(pass, admin.getPassword()))
           return false;

       return true;
    }

}
