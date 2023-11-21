package com.prathamesh.decentralizedHealthcareBackend.service;

import com.prathamesh.decentralizedHealthcareBackend.config.IPFSConfig;
import com.prathamesh.decentralizedHealthcareBackend.entity.Doctor;
import com.prathamesh.decentralizedHealthcareBackend.entity.Hospital;
import com.prathamesh.decentralizedHealthcareBackend.entity.Record;
import com.prathamesh.decentralizedHealthcareBackend.entity.RecordModel;
import com.prathamesh.decentralizedHealthcareBackend.repository.DoctorRepository;
import com.prathamesh.decentralizedHealthcareBackend.repository.HospitalRepository;
import com.prathamesh.decentralizedHealthcareBackend.repository.RecordRepository;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecordService implements RecordServiceImpl{

    Logger logger = LoggerFactory.getLogger(RecordService.class);

    @Autowired
    IPFSConfig ipfsConfig;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public RecordModel saveRecord(RecordModel recordModel) {

        String filename = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10),"ser" );

        Record record = new Record();
        record.setPatientId(recordModel.getPatientId());
        record.setDoctorId(recordModel.getDoctorId());
        record.setHospitalId(recordModel.getHospitalId());

        try {

            File file = new File("src/main/java/temp/"+filename);
            FileOutputStream fileOutputStream =new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(recordModel);

            fileOutputStream.close();
            objectOutputStream.close();

            IPFS ipfs =ipfsConfig.ipfs;

            NamedStreamable.FileWrapper ipfsFile =new NamedStreamable.FileWrapper(file);
            MerkleNode res = ipfs.add(ipfsFile).get(0);

            logger.error("HashCode ->"+res.hash);

            record.setHash(String.valueOf(res.hash));

            recordRepository.save(record);

           file.delete();

        }catch (Exception e){
            e.printStackTrace();
        }

        return recordModel;
    }

    public List<RecordModel> getPatientRecords(String id){

        IPFS ipfs = ipfsConfig.ipfs;

        List<String> hashCodes = recordRepository.getRecordHashByPatientId(id);

        List<RecordModel> records = new ArrayList<>();

        for (String s : hashCodes){
            Multihash multihash = Multihash.fromBase58(s);

            String filename = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(10),"ser" );

            try {
                byte[] array = ipfs.cat(multihash);


                File file = new File("src/main/java/temp/"+filename);
                FileOutputStream fileOutputStream =new FileOutputStream(file);
                fileOutputStream.write(array);

                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                RecordModel recordModel = (RecordModel) objectInputStream.readObject();

                Doctor doctor = doctorRepository.findFirstByDoctorId(recordModel.getDoctorId());

                Hospital hospital = hospitalRepository.findFirstByHospitalId(recordModel.getHospitalId());

                recordModel.setDoctor(doctor);
                recordModel.setHospital(hospital);

               records.add(recordModel);

               fileOutputStream.close();
               fileInputStream.close();
               objectInputStream.close();
               file.delete();

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }




        return records;
    }
}
