package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Hospital;

public interface HospitalServiceImpl {
    public Hospital signUpHospital(Hospital hospital);
    public boolean loginHospital(String id, String pass);
}
