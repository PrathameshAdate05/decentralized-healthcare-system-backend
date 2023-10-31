package com.prathamesh.decentralizedHealthcareBackend.service.jwt;

import com.prathamesh.decentralizedHealthcareBackend.entity.*;
import com.prathamesh.decentralizedHealthcareBackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ChemistRepository chemistRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findFirstById(username);

        Patient patient = patientRepository.findFirstByPatientId(username);

        Chemist chemist = chemistRepository.findFirstByChemistId(username);

        Doctor doctor = doctorRepository.findFirstByDoctorId(username);

        Hospital hospital = hospitalRepository.findFirstByHospitalId(username);

        if (admin != null)
            return new User(admin.getId(), admin.getPassword(), new ArrayList<>());

        if (patient != null)
            return new User(patient.getPatientId(), patient.getPassword(), new ArrayList<>());

        if (chemist != null)
            return new User(chemist.getChemistId(), chemist.getPassword(), new ArrayList<>());

        if (doctor != null)
            return new User(doctor.getDoctorId(), doctor.getPassword(), new ArrayList<>());

        if (hospital != null)
            return new User(hospital.getHospitalId(), hospital.getPassword(), new ArrayList<>());

        return null;
    }
}
