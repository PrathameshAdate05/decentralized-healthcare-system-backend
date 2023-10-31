package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, String> {

    @Query(value = "select * from patient where patient_Id=?1", nativeQuery = true)
    Patient findFirstByPatientId(String username);
}
