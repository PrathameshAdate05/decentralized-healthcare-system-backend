package com.prathamesh.decentralizedHealthcareBackend.generator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class CustomHospitalIdGenerator implements IdentifierGenerator {


    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) {
        HashSet<String> set = new HashSet<>();
        String id = "";

        try {

            sharedSessionContractImplementor.doWork(connection -> {
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select hospitalId from hospital");

                    while (resultSet.next()){
                        set.add(resultSet.getString("hospitalId"));
                    }


                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });


            id = generateId();
            while (set.contains(id)){
                id = generateId();
            }

            return id;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateId(){
        String id = "H" + RandomStringUtils.randomNumeric(12, 12);
        return id;
    }
}
