package com.Landmine_Soft.Hospital_Management_System.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DoctorProfileRequestDto {

    private String position;
    private String department;
    private BigDecimal feesPerConsult;
    private String clinicAddress;
    private Integer experienceYears;
    private String specialization;
}

