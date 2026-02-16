package com.Landmine_Soft.Hospital_Management_System.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponseDto {

    private String jwtToken;
    private String role;
}

