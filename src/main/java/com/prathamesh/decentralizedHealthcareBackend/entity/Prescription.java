package com.prathamesh.decentralizedHealthcareBackend.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Prescription implements Serializable {

    String pid;

    List<Medicine> medicines;

    String patientId;

    String doctorId;

    String hospitalId;

    String createdAt;
}
