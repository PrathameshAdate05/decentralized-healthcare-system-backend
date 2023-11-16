package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Patient;
import com.prathamesh.decentralizedHealthcareBackend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements PatientServiceImpl{

    @Autowired
    PatientRepository patientRepository;
    @Override
    public Patient signUpPatient(Patient patient) {

        String pass = patient.getPassword();
        patient.setPassword(new BCryptPasswordEncoder().encode(pass));

        return patientRepository.save(patient);
    }

    @Override
    public boolean loginPatient(String id, String pass) {
        Patient patient = patientRepository.findFirstByPatientId(id);

        if (patient == null)
            return false;

        if (!new BCryptPasswordEncoder().matches(pass, patient.getPassword()))
            return false;

        return true;
    }
}
