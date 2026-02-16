package com.Landmine_Soft.Hospital_Management_System.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {

    private Long id;
    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String city;
    private String gender;
    private String bloodGroup;
}

