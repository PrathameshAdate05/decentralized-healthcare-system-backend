package com.prathamesh.decentralizedHealthcareBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GenericGenerator(name = "custom_doctor_id_generator", strategy = "com.prathamesh.decentralizedHealthcareBackend.generator.CustomDoctorIdGenerator")
    @GeneratedValue(generator = "custom_doctor_id_generator")
    String doctorId;

    String firstName;

    String middleName;

    String lastName;

    String email;

    String password;

    String contact;

    String qualification;

    Integer age;

    String gender;

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
