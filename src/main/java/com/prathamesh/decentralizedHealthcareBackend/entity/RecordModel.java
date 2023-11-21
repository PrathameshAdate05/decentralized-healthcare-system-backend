package com.prathamesh.decentralizedHealthcareBackend.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecordModel implements Serializable {

    String doctorId;

    String patientId;

    String hospitalId;

    Prescription prescription;

    String diagnosis;

    String description;

    Date admissionDate;

    Date dischargeDate;

    transient Doctor doctor;

    transient Hospital hospital;
}
