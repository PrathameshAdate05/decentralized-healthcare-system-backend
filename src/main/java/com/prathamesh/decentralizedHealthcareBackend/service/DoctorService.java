package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Doctor;
import com.prathamesh.decentralizedHealthcareBackend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.print.Doc;

@Service
public class DoctorService implements DoctorServiceImpl{

    @Autowired
    DoctorRepository doctorRepository;
    @Override
    public Doctor signUpDoctor(Doctor doctor) {

        String pass = doctor.getPassword();
        doctor.setPassword(new BCryptPasswordEncoder().encode(pass));

        return doctorRepository.save(doctor);
    }

    @Override
    public boolean loginDoctor(String id, String pass) {
        Doctor doctor = doctorRepository.findFirstByDoctorId(id);

        if (doctor == null || !new BCryptPasswordEncoder().matches(pass, doctor.getPassword()))
            return false;

        return true;
    }
}
