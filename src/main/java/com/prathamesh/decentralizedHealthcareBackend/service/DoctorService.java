package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Doctor;
import com.prathamesh.decentralizedHealthcareBackend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements DoctorServiceImpl{

    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Doctor signUpDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
