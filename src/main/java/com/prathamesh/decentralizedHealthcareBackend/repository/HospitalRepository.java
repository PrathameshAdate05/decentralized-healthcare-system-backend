package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital, String> {

    @Query(value = "select * from hospital where hospital_Id=?1", nativeQuery = true)
    Hospital findFirstByHospitalId(String username);
}
