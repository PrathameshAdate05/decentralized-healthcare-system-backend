package com.prathamesh.decentralizedHealthcareBackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GenericGenerator(name = "custom_patient_id_generator", strategy = "com.prathamesh.decentralizedHealthcareBackend.generator.CustomPatientIdGenerator")
    @GeneratedValue(generator = "custom_patient_id_generator")
    String patientId;

    String firstName;

    String middleName;

    String lastName;

    String email;

    String password;

    String contact;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date DOB;

    Integer age;

    String gender;

    Boolean isAlive;

    String address;

    String city;

    String state;

    String pincode;

    String country;

    @CreationTimestamp
    String createdAt;

    @UpdateTimestamp
    String updatedAt;
}
