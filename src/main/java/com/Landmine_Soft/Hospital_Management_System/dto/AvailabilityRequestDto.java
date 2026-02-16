package com.Landmine_Soft.Hospital_Management_System.dto;


import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AvailabilityRequestDto {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}

