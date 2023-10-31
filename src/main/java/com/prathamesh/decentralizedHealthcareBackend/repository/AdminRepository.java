package com.prathamesh.decentralizedHealthcareBackend.repository;

import com.prathamesh.decentralizedHealthcareBackend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "select * from admin where id=?1", nativeQuery = true)
    Admin findFirstById(String username);
}
