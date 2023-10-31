package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Chemist;
import com.prathamesh.decentralizedHealthcareBackend.repository.ChemistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChemistService implements ChemistServiceImpl{

    @Autowired
    ChemistRepository chemistRepository;
    @Override
    public Chemist signUpChemist(Chemist chemist) {
        return chemistRepository.save(chemist);
    }
}
