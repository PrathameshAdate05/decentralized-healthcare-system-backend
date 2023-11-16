package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Hospital;
import com.prathamesh.decentralizedHealthcareBackend.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HospitalService implements HospitalServiceImpl{

    @Autowired
    HospitalRepository hospitalRepository;
    @Override
    public Hospital signUpHospital(Hospital hospital) {
        String pass = hospital.getPassword();
        hospital.setPassword(new BCryptPasswordEncoder().encode(pass));
        return hospitalRepository.save(hospital);
    }

    @Override
    public boolean loginHospital(String id, String pass) {
        Hospital hospital = hospitalRepository.findFirstByHospitalId(id);

        if (hospital == null || !new BCryptPasswordEncoder().matches(pass, hospital.getPassword()))
            return false;

        return true;
    }
}
