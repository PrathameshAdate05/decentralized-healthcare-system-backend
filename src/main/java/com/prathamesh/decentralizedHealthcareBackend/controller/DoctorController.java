package com.prathamesh.decentralizedHealthcareBackend.controller;

import com.prathamesh.decentralizedHealthcareBackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/doctor")
@CrossOrigin(origins = "http://localhost:3000")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

}
