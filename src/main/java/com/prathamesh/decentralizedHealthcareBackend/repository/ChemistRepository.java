package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.entity.Chemist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChemistRepository extends JpaRepository<Chemist, String> {

    @Query(value = "select * from chemist where chemist_Id=?1", nativeQuery = true)
    Chemist findFirstByChemistId(String username);
}
