package com.prathamesh.decentralizedHealthcareBackend.controller;

import com.prathamesh.decentralizedHealthcareBackend.dto.AuthDTO;
import com.prathamesh.decentralizedHealthcareBackend.entity.*;
import com.prathamesh.decentralizedHealthcareBackend.service.*;
import com.prathamesh.decentralizedHealthcareBackend.util.Constants;
import com.prathamesh.decentralizedHealthcareBackend.util.JwtHelper;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.regex.Pattern;


@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "http://localhost:3000")
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
    public String test(@RequestHeader("Authorization") HashMap<String, String> map) {


        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
            if (jwtHelper.validateToken(token))
                return "Hello Spring";

        } catch (Exception e) {
            return "Unauthorized";
        }
        return "Unauthorized";
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<Object> adminSignUp(@RequestBody Admin admin) {

        if (admin.getFirstName().isEmpty())
            return new ResponseEntity<>("First Name can't be empty.", HttpStatus.BAD_REQUEST);
        else if (admin.getMiddleName().isEmpty())
            return new ResponseEntity<>("Middle Name can't be empty.", HttpStatus.BAD_REQUEST);
        else if (admin.getLastName().isEmpty())
            return new ResponseEntity<>("Last Name can't be empty.", HttpStatus.BAD_REQUEST);
        else if (admin.getEmail().isEmpty())
            return new ResponseEntity<>("Email can't be empty.", HttpStatus.BAD_REQUEST);
        else if (!Pattern.matches(Constants.emailRegex, admin.getEmail()))
            return new ResponseEntity<>("Enter Valid Email.", HttpStatus.BAD_REQUEST);
        else if (admin.getPassword().isEmpty())
            return new ResponseEntity<>("Password can't be empty.", HttpStatus.BAD_REQUEST);
        else if (admin.getPassword().length() < 6)
            return new ResponseEntity<>("Password must contain at least 6 characters", HttpStatus.BAD_REQUEST);
        else if (admin.getContact().isEmpty())
            return new ResponseEntity<>("Contact No can't be empty.", HttpStatus.BAD_REQUEST);
        else {
            Admin temp = adminService.signUpAdmin(admin);

            AuthDTO authDTO = new AuthDTO();
            authDTO.setUsername(temp.getId());
            authDTO.setRole("ADMIN");
            authDTO.setPassword(temp.getPassword());

            authService.signUpAuth(authDTO);

            HashMap<Object, Object> res = new HashMap<>();

            res.put("status", "success");
            res.put("user", temp);

            if (temp != null) {
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/signup/patient")
    public ResponseEntity<Object> patientSignUp(@RequestBody Patient patient, @RequestHeader("Authorization") HashMap<String, String> map) {

        if (map.get("authorization") == null)
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);

        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
            if (jwtHelper.validateToken(token)) {

                if (patient.getEmail().isEmpty())
                    return new ResponseEntity<>("Email can't be empty.", HttpStatus.BAD_REQUEST);
                else if (!Pattern.matches(Constants.emailRegex, patient.getEmail()))
                    return new ResponseEntity<>("Enter Valid Email.", HttpStatus.BAD_REQUEST);
                else if (patient.getPassword().isEmpty())
                    return new ResponseEntity<>("Password can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getPassword().length() < 6)
                    return new ResponseEntity<>("Password must contain at least 6 characters", HttpStatus.BAD_REQUEST);
                else if (patient.getFirstName().isEmpty())
                    return new ResponseEntity<>("First Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getLastName().isEmpty())
                    return new ResponseEntity<>("Last Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getAge() <= 0)
                    return new ResponseEntity<>("Enter valid Age.", HttpStatus.BAD_REQUEST);
                else if (patient.getAddress().isEmpty())
                    return new ResponseEntity<>("Address can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getCity().isEmpty())
                    return new ResponseEntity<>("City can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getState().isEmpty())
                    return new ResponseEntity<>("State can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getCountry().isEmpty())
                    return new ResponseEntity<>("Country can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getMiddleName().isEmpty())
                    return new ResponseEntity<>("Middle Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getContact().isEmpty())
                    return new ResponseEntity<>("Contact No can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getContact().length() != 10)
                    return new ResponseEntity<>("Enter valid Contact No", HttpStatus.BAD_REQUEST);
                else if (patient.getGender().isEmpty())
                    return new ResponseEntity<>("Gender can't be empty.", HttpStatus.BAD_REQUEST);
                else if (patient.getPincode().isEmpty())
                    return new ResponseEntity<>("Pin Code can't be empty.", HttpStatus.BAD_REQUEST);
                else {
                    Patient temp = patientService.signUpPatient(patient);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setUsername(temp.getPatientId());
                    authDTO.setRole("PATIENT");
                    authDTO.setPassword(temp.getPassword());

                    authService.signUpAuth(authDTO);

                    HashMap<Object, Object> res = new HashMap<>();

                    res.put("status", "success");
                    res.put("user", temp);

                    if (temp != null) {
                        return new ResponseEntity<>(res, HttpStatus.OK);
                    }

                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }

            }

        } catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);


    }

    @PostMapping("/signup/doctor")
    public ResponseEntity<Object> doctorSignUp(@RequestBody Doctor doctor, @RequestHeader("Authorization") HashMap<String, String> map) {

        if (map.get("authorization") == null)
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);

        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
            if (jwtHelper.validateToken(token)) {

                if (doctor.getFirstName().isEmpty())
                    return new ResponseEntity<>("First Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getMiddleName().isEmpty())
                    return new ResponseEntity<>("Middle Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getLastName().isEmpty())
                    return new ResponseEntity<>("Last Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getEmail().isEmpty())
                    return new ResponseEntity<>("Email can't be empty.", HttpStatus.BAD_REQUEST);
                else if (!Pattern.matches(Constants.emailRegex, doctor.getEmail()))
                    return new ResponseEntity<>("Enter Valid Email.", HttpStatus.BAD_REQUEST);
                else if (doctor.getPassword().isEmpty())
                    return new ResponseEntity<>("Password can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getPassword().length() < 6)
                    return new ResponseEntity<>("Password must contain at least 6 characters", HttpStatus.BAD_REQUEST);
                else if (doctor.getContact().isEmpty())
                    return new ResponseEntity<>("Contact No can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getQualification().isEmpty())
                    return new ResponseEntity<>("Qualification can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getAge() <= 0)
                    return new ResponseEntity<>("Enter valid age.", HttpStatus.BAD_REQUEST);
                else if (doctor.getGender().isEmpty())
                    return new ResponseEntity<>("Gender can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getAddress().isEmpty())
                    return new ResponseEntity<>("Address can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getCity().isEmpty())
                    return new ResponseEntity<>("City can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getState().isEmpty())
                    return new ResponseEntity<>("State can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getCountry().isEmpty())
                    return new ResponseEntity<>("Country can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getPincode().isEmpty())
                    return new ResponseEntity<>("PinCode can't be empty.", HttpStatus.BAD_REQUEST);
                else if (doctor.getContact().length() != 10)
                    return new ResponseEntity<>("Enter valid Contact No.", HttpStatus.BAD_REQUEST);
                else {
                    Doctor temp = doctorService.signUpDoctor(doctor);

                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setUsername(temp.getDoctorId());
                    authDTO.setRole("DOCTOR");
                    authDTO.setPassword(temp.getPassword());

                    authService.signUpAuth(authDTO);

                    HashMap<Object, Object> res = new HashMap<>();

                    res.put("status", "success");
                    res.put("user", temp);


                    if (temp != null) {
                        return new ResponseEntity<>(res, HttpStatus.OK);
                    }

                }

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);


    }

    @PostMapping("/signup/chemist")
    public ResponseEntity<Object> chemistSignUp(@RequestBody Chemist chemist, @RequestHeader("Authorization") HashMap<String, String> map) {

        if (map.get("authorization") == null)
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);

        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
            if (jwtHelper.validateToken(token)) {

                if (chemist.getFirstName().isEmpty())
                    return new ResponseEntity<>("First Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getMiddleName().isEmpty())
                    return new ResponseEntity<>("Middle Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getLastName().isEmpty())
                    return new ResponseEntity<>("Last Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getEmail().isEmpty())
                    return new ResponseEntity<>("Email can't be empty.", HttpStatus.BAD_REQUEST);
                else if (!Pattern.matches(Constants.emailRegex, chemist.getEmail()))
                    return new ResponseEntity<>("Enter Valid Email.", HttpStatus.BAD_REQUEST);
                else if (chemist.getPassword().isEmpty())
                    return new ResponseEntity<>("Password can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getPassword().length() < 6)
                    return new ResponseEntity<>("Password must contain at least 6 characters", HttpStatus.BAD_REQUEST);
                else if (chemist.getContact().isEmpty())
                    return new ResponseEntity<>("Contact No can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getQualification().isEmpty())
                    return new ResponseEntity<>("Qualification can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getAddress().isEmpty())
                    return new ResponseEntity<>("Address can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getCity().isEmpty())
                    return new ResponseEntity<>("City can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getState().isEmpty())
                    return new ResponseEntity<>("State can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getCountry().isEmpty())
                    return new ResponseEntity<>("Country can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getPincode().isEmpty())
                    return new ResponseEntity<>("PinCode can't be empty.", HttpStatus.BAD_REQUEST);
                else if (chemist.getContact().length() != 10)
                    return new ResponseEntity<>("Enter valid Contact No.", HttpStatus.BAD_REQUEST);
                else if (chemist.getShopName().isEmpty())
                    return new ResponseEntity<>("Shop Name can't be empty.", HttpStatus.BAD_REQUEST);
                else {
                    Chemist temp = chemistService.signUpChemist(chemist);

                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setUsername(temp.getChemistId());
                    authDTO.setRole("CHEMIST");
                    authDTO.setPassword(temp.getPassword());

                    authService.signUpAuth(authDTO);

                    HashMap<Object, Object> res = new HashMap<>();

                    res.put("status", "success");
                    res.put("user", temp);

                    if (temp != null) {
                        return new ResponseEntity<>(res, HttpStatus.OK);
                    }
                }

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);


    }

    @PostMapping("/signup/hospital")
    public ResponseEntity<Object> hospitalSignUp(@RequestBody Hospital hospital, @RequestHeader("Authorization") HashMap<String, String> map) {

        if (map.get("authorization") == null)
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);

        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
            if (jwtHelper.validateToken(token)) {

                if (hospital.getName().isEmpty())
                    return new ResponseEntity<>("Hospital Name can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getEmail().isEmpty())
                    return new ResponseEntity<>("Email can't be empty.", HttpStatus.BAD_REQUEST);
                else if (!Pattern.matches(Constants.emailRegex, hospital.getEmail()))
                    return new ResponseEntity<>("Enter Valid Email.", HttpStatus.BAD_REQUEST);
                else if (hospital.getPassword().isEmpty())
                    return new ResponseEntity<>("Password can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getPassword().length() < 6)
                    return new ResponseEntity<>("Password must contain at least 6 characters", HttpStatus.BAD_REQUEST);
                else if (hospital.getContact().isEmpty())
                    return new ResponseEntity<>("Contact No can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getAddress().isEmpty())
                    return new ResponseEntity<>("Address can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getCity().isEmpty())
                    return new ResponseEntity<>("City can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getState().isEmpty())
                    return new ResponseEntity<>("State can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getCountry().isEmpty())
                    return new ResponseEntity<>("Country can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getPincode().isEmpty())
                    return new ResponseEntity<>("PinCode can't be empty.", HttpStatus.BAD_REQUEST);
                else if (hospital.getContact().length() != 10)
                    return new ResponseEntity<>("Enter valid Contact No.", HttpStatus.BAD_REQUEST);
                else if (hospital.getType().isEmpty())
                    return new ResponseEntity<>("Hospital Type can't be empty.", HttpStatus.BAD_REQUEST);
                else {
                    Hospital temp = hospitalService.signUpHospital(hospital);

                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setUsername(temp.getHospitalId());
                    authDTO.setRole("HOSPITAL");
                    authDTO.setPassword(temp.getPassword());

                    authService.signUpAuth(authDTO);

                    HashMap<Object, Object> res = new HashMap<>();

                    res.put("status", "success");
                    res.put("user", temp);

                    if (temp != null) {
                        return new ResponseEntity<>(res, HttpStatus.OK);
                    }
                }

                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }


        } catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);


    }

    @PostMapping("/login/admin")
    public ResponseEntity<Object> adminLogin(@RequestBody HashMap<String, String> map) {
        String username = map.get("username");
        String pass = map.get("password");
        String role = map.get("role");

        if (adminService.loginAdmin(username, pass)) {
            String token = jwtHelper.generateToken(username);

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Email or Password is Invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/patient")
    public ResponseEntity<Object> patientLogin(@RequestBody HashMap<String, String> map) {
        String username = map.get("username");
        String pass = map.get("password");

        if (patientService.loginPatient(username, pass)) {
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("User id or Password is Invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/doctor")
    public ResponseEntity<Object> doctorLogin(@RequestBody HashMap<String, String> map) {
        String username = map.get("username");
        String pass = map.get("password");

        if (doctorService.loginDoctor(username, pass)) {
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("User id or Password is Invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/chemist")
    public ResponseEntity<Object> chemistLogin(@RequestBody HashMap<String, String> map) {
        String username = map.get("username");
        String pass = map.get("password");

        if (chemistService.loginChemist(username, pass)) {
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("User id or Password is Invalid", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login/hospital")
    public ResponseEntity<Object> hospitalLogin(@RequestBody HashMap<String, String> map) {
        String username = map.get("username");
        String pass = map.get("password");

        if (hospitalService.loginHospital(username, pass)) {
            String token = jwtHelper.generateToken(username);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("User id or Password is Invalid", HttpStatus.BAD_REQUEST);
    }


}
