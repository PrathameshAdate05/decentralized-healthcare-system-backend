package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Patient;
import com.prathamesh.decentralizedHealthcareBackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements PatientServiceImpl{

    @Autowired
    PatientRepository patientRepository;
    @Override
    public Patient signUpPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
