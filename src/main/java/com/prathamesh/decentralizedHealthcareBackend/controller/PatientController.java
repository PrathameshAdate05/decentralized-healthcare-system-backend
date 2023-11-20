package com.prathamesh.decentralizedHealthcareBackend.controller;

import com.prathamesh.decentralizedHealthcareBackend.entity.Patient;
import com.prathamesh.decentralizedHealthcareBackend.entity.Prescription;
import com.prathamesh.decentralizedHealthcareBackend.entity.RecordModel;
import com.prathamesh.decentralizedHealthcareBackend.service.PatientService;
import com.prathamesh.decentralizedHealthcareBackend.service.RecordService;
import com.prathamesh.decentralizedHealthcareBackend.util.JwtHelper;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/data")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

    Logger logger = LoggerFactory.getLogger(PatientController.class);
    @Autowired
    PatientService patientService;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    RecordService recordService;

    @GetMapping("/test")
    public String test(){
        return "Hello Spring";
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Object> getPatient(@RequestHeader("Authorization") HashMap<String, String> map, @PathVariable String id){

        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
            if (jwtHelper.validateToken(token))
                if (jwtHelper.validateToken(token)){
                    Patient patient = patientService.getPatientInfo(id);
                    List<RecordModel> records = recordService.getPatientRecords(id);
                    HashMap<String, Object> res = new HashMap<>();
                    res.put("user",patient);
                    res.put("records", records);

                    return new ResponseEntity<>(res, HttpStatus.OK);
                }

        }catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/patient/add-record")
    public ResponseEntity<Object> addRecord(@RequestHeader("Authorization") HashMap<String, String> map, @RequestBody RecordModel recordModel){
        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
                if (jwtHelper.validateToken(token)){

                    Prescription p = recordModel.getPrescription();
                    p.setPid(RandomStringUtils.randomNumeric(10));
                    recordModel.setPrescription(p);

                    RecordModel temp = recordService.saveRecord(recordModel);


                   return new ResponseEntity<>(temp, HttpStatus.OK);
                }

        }catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping ("/patient/records/{id}")
    public ResponseEntity<Object> getRecords(@RequestHeader("Authorization") HashMap<String, String> map, @PathVariable String id){
        String token = map.get("authorization").substring(7);

        logger.error(token);

        try {
            if (jwtHelper.validateToken(token)){
                logger.error("comes here");
                List<RecordModel> records = recordService.getPatientRecords(id);
                return new ResponseEntity<>(records, HttpStatus.OK);
            }

        }catch (Exception e) {
            return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("Access Denied", HttpStatus.UNAUTHORIZED);
    }
}
