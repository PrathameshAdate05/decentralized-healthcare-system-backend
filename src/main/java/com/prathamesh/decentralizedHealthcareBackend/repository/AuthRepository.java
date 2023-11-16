package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.dto.AuthDTO;
import com.prathamesh.decentralizedHealthcareBackend.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepository extends JpaRepository<Auth, String> {

    @Query(value = "select * from auth where username=?1", nativeQuery = true)
    public Auth findAuthById(String id);
}
