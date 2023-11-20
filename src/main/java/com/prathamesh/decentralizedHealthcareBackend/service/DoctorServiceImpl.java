package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Doctor;

import javax.print.Doc;

public interface DoctorServiceImpl {
    public Doctor signUpDoctor(Doctor doctor);
    public boolean loginDoctor(String id, String pass);

    public Doctor getDoctorInfo(String id);
}
