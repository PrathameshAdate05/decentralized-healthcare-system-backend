package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Chemist;

public interface ChemistServiceImpl {
    public Chemist signUpChemist(Chemist chemist);
    public boolean loginChemist(String id, String pass);
}
