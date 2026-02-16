package com.Landmine_Soft.Hospital_Management_System.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorProfile {

    @Id
    private Long id; // Same as Doctor ID

    @OneToOne
    @MapsId
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String city;

    private String position;
    private String department;
    private BigDecimal feesPerConsult;

    @Column(columnDefinition = "TEXT")
    private String clinicAddress;

    private Integer experienceYears;

    private Double rating = 0.0;

    private String specialization;
}

