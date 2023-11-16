package com.prathamesh.decentralizedHealthcareBackend.controller;

import com.prathamesh.decentralizedHealthcareBackend.dto.AuthDTO;
import com.prathamesh.decentralizedHealthcareBackend.entity.*;
import com.prathamesh.decentralizedHealthcareBackend.service.*;
import com.prathamesh.decentralizedHealthcareBackend.util.JwtHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);
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

    @Autowired
    AuthService authService;

    @Autowired
    JwtHelper jwtHelper;

    @PostMapping("/test")
    public String test(@RequestHeader("Authorization")HashMap<String, String> map){


        String token = map.get("authorization").substring(7);

        logger.error(token);

        if (jwtHelper.validateToken(token))
            return "Hello Spring";

        return "Unauthorized";
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<Object> adminSignUp(@RequestBody Admin admin){
        Admin temp = adminService.signUpAdmin(admin);

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(temp.getId());
        authDTO.setRole("ADMIN");
        authDTO.setPassword(temp.getPassword());

        authService.signUpAuth(authDTO);

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
        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(temp.getPatientId());
        authDTO.setRole("PATIENT");
        authDTO.setPassword(temp.getPassword());

        authService.signUpAuth(authDTO);

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

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(temp.getDoctorId());
        authDTO.setRole("DOCTOR");
        authDTO.setPassword(temp.getPassword());

        authService.signUpAuth(authDTO);

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

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(temp.getChemistId());
        authDTO.setRole("CHEMIST");
        authDTO.setPassword(temp.getPassword());

        authService.signUpAuth(authDTO);

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

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(temp.getHospitalId());
        authDTO.setRole("HOSPITAL");
        authDTO.setPassword(temp.getPassword());

        authService.signUpAuth(authDTO);

        HashMap<Object, Object> res = new HashMap<>();

        res.put("status","success");
        res.put("user",temp);

        if (temp != null){
            return new ResponseEntity<>(res, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<Object> adminLogin(@RequestBody HashMap<String, String> map){
        String username  = map.get("username");
        String pass = map.get("password");
        String role  = map.get("role");

        if (adminService.loginAdmin(username, pass)){
            String token = jwtHelper.generateToken(username);

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Email or Password is Invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/patient")
    public ResponseEntity<Object> patientLogin(@RequestBody HashMap<String, String> map){
        String username  = map.get("username");
        String pass = map.get("password");

        if (patientService.loginPatient(username, pass)){
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Email or Password is Invalid", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/login/doctor")
    public ResponseEntity<Object> doctorLogin(@RequestBody HashMap<String, String> map){
        String username  = map.get("username");
        String pass = map.get("password");

        if (doctorService.loginDoctor(username, pass)){
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Email or Password is Invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/chemist")
    public ResponseEntity<Object> chemistLogin(@RequestBody HashMap<String, String> map){
        String username  = map.get("username");
        String pass = map.get("password");

        if (chemistService.loginChemist(username, pass)){
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Email or Password is Invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/hospital")
    public ResponseEntity<Object> hospitalLogin(@RequestBody HashMap<String, String> map){
        String username  = map.get("username");
        String pass = map.get("password");

        if (hospitalService.loginHospital(username, pass)){
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Email or Password is Invalid", HttpStatus.BAD_REQUEST);
    }


}
