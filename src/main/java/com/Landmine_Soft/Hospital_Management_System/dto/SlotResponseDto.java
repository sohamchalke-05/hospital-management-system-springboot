package com.Landmine_Soft.Hospital_Management_System.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class SlotResponseDto {

    private String timeSlot;
    private Boolean isBooked;
}

