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
@Table(name = "chemist")
public class Chemist {

    @Id
    @GenericGenerator(name = "custom_chemist_id_generator", strategy = "com.prathamesh.decentralizedHealthcareBackend.generator.CustomChemistIdGenerator")
    @GeneratedValue(generator = "custom_chemist_id_generator")
    String chemistId;

    String firstName;

    String middleName;

    String lastName;

    String email;

    String password;

    String contact;

    String qualification;

    String shopName;

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
