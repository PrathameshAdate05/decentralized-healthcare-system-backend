package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Admin;

public interface AdminServiceImpl {

    public Admin signUpAdmin(Admin admin);

    public boolean loginAdmin(String email, String pass);

}
