package com.prathamesh.decentralizedHealthcareBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String email;

    String password;

    String firstName;

    String middleName;

    String lastName;

    String contact;
}
