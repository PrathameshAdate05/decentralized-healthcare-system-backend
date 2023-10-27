package com.prathamesh.decentralizedHealthcareBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "hospital")
public class Hospital {

    @Id
    @GenericGenerator(name = "custom_hospital_id_generator", strategy = "com.prathamesh.decentralizedHealthcareBackend.generator.CustomHospitalIdGenerator")
    @GeneratedValue(generator = "custom_hospital_id_generator")
    String hospitalId;

    String name;

    String email;

    String password;

    String address;

    String city;

    String state;

    String pincode;

    String country;

    String contact;

    String type;

    @CreationTimestamp
    String createdAt;

    @UpdateTimestamp
    String updatedAt;
}
