package com.prathamesh.decentralizedHealthcareBackend.controller;


import com.prathamesh.decentralizedHealthcareBackend.entity.Hospital;
import com.prathamesh.decentralizedHealthcareBackend.service.HospitalService;
import com.prathamesh.decentralizedHealthcareBackend.util.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("api/hospital")
@CrossOrigin(origins = "http://localhost:3000")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @Autowired
    JwtHelper jwtHelper;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHospitalInfo(@RequestHeader("Authorization") HashMap<String, String> map, @PathVariable String id){
        if (map.get("authorization") == null)
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);

        String token = map.get("authorization").substring(7);



        try {
            if (jwtHelper.validateToken(token)){

                Hospital hospital = hospitalService.getHospitalInfo(id);

                if (hospital == null)
                    return new ResponseEntity<>("Hospital Not Found!!!", HttpStatus.BAD_REQUEST);

                return new ResponseEntity<>(hospital, HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
    }
}
