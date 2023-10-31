package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, String> {

    @Query(value = "select * from doctor where doctor_Id=?1", nativeQuery = true)
    Doctor findFirstByDoctorId(String username);
}
