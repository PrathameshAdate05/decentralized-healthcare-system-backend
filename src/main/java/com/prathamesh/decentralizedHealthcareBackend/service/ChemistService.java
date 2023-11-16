package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.entity.Chemist;
import com.prathamesh.decentralizedHealthcareBackend.repository.ChemistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ChemistService implements ChemistServiceImpl{

    @Autowired
    ChemistRepository chemistRepository;
    @Override
    public Chemist signUpChemist(Chemist chemist) {
        String pass = chemist.getPassword();
        chemist.setPassword(new BCryptPasswordEncoder().encode(pass));
        return chemistRepository.save(chemist);
    }

    @Override
    public boolean loginChemist(String id, String pass) {
        Chemist chemist = chemistRepository.findFirstByChemistId(id);

        if (chemist == null || !new BCryptPasswordEncoder().matches(pass, chemist.getPassword()))
            return false;

        return true;
    }
}
