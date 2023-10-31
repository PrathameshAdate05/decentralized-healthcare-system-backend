package com.prathamesh.decentralizedHealthcareBackend.controller;

import com.prathamesh.decentralizedHealthcareBackend.entity.*;
import com.prathamesh.decentralizedHealthcareBackend.service.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AdminService adminService;

    @Autowired
    PatientService patientService;

    @Autowired
    ChemistService chemistService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    HospitalService hospitalService;

    @GetMapping("/test")
    public String test(){
        return "Hello Spring";
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<Object> adminSignUp(@RequestBody Admin admin){
        Admin temp = adminService.signUpAdmin(admin);

        HashMap<Object, Object> res = new HashMap<>();

        res.put("status","success");
        res.put("user",temp);

        if (temp != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup/patient")
    public ResponseEntity<Object> patientSignUp(@RequestBody Patient patient){

        Patient temp = patientService.signUpPatient(patient);

        HashMap<Object, Object> res = new HashMap<>();

        res.put("status","success");
        res.put("user",temp);

        if (temp != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup/doctor")
    public ResponseEntity<Object> doctorSignUp(@RequestBody Doctor doctor){

        Doctor temp = doctorService.signUpDoctor(doctor);

        HashMap<Object, Object> res = new HashMap<>();

        res.put("status","success");
        res.put("user",temp);

        if (temp != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup/chemist")
    public ResponseEntity<Object> chemistSignUp(@RequestBody Chemist chemist){

        Chemist temp = chemistService.signUpChemist(chemist);

        HashMap<Object, Object> res = new HashMap<>();

        res.put("status","success");
        res.put("user",temp);

        if (temp != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup/hospital")
    public ResponseEntity<Object> hospitalSignUp(@RequestBody Hospital hospital){

        Hospital temp = hospitalService.signUpHospital(hospital);

        HashMap<Object, Object> res = new HashMap<>();

        res.put("status","success");
        res.put("user",temp);

        if (temp != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
