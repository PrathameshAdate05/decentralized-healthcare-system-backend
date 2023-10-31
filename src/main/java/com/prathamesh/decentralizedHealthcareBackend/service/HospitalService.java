package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Hospital;
import com.prathamesh.decentralizedHealthcareBackend.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService implements HospitalServiceImpl{

    @Autowired
    HospitalRepository hospitalRepository;
    @Override
    public Hospital signUpHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }
}
