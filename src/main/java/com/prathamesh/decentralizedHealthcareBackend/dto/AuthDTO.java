package com.prathamesh.decentralizedHealthcareBackend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AuthDTO {

    String username;
    String password;
    String role;
}
