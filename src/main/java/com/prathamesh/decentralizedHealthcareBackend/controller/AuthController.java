package com.prathamesh.decentralizedHealthcareBackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @GetMapping("/patient/login")
    public String patientLogin(){
        return "Patient Login";
    }
}
