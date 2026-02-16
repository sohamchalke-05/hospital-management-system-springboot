package com.Landmine_Soft.Hospital_Management_System.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDto {

    private Long id;
    private String name;
    private String department;
    private BigDecimal feesPerConsult;
    private String clinicAddress;
    private Integer experienceYears;
    private Double rating;
    private String specialization;
}

