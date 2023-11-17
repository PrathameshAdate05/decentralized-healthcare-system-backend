package com.prathamesh.decentralizedHealthcareBackend.entity;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Medicine implements Serializable {

    String name;
    String description;
}
