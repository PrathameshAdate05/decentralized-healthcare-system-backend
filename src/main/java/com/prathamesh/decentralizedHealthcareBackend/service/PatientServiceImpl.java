package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Patient;

public interface PatientServiceImpl {
    public Patient signUpPatient(Patient patient);
    public boolean loginPatient(String id, String pass);

    public Patient getPatientInfo(String id);
}
