package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.entity.Chemist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemistRepository extends JpaRepository<Chemist, String> {
}
