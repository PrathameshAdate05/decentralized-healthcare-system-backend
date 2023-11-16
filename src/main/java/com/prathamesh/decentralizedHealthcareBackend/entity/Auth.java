package com.prathamesh.decentralizedHealthcareBackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "auth")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Auth {

    @Id
    String username;

    String password;
    String role;
}
