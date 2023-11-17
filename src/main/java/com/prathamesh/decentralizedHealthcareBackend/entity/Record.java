package com.prathamesh.decentralizedHealthcareBackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Reference;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "records")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String recordId;

    String doctorId;

    String patientId;

    String hospitalId;

    String hash;

    @CreationTimestamp
    String createdAt;
}
