package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, String> {

    @Query(value = "select hash from records where patient_id=?1", nativeQuery = true)
    public List<String> getRecordHashByPatientId(String id);
}
