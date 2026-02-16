package com.Landmine_Soft.Hospital_Management_System.entity;

import com.Landmine_Soft.Hospital_Management_System.entity.enums.Gender;
import com.Landmine_Soft.Hospital_Management_System.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;


    @Column(columnDefinition = "TEXT")
    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String bloodGroup;
    private String emergencyContact;
    private String city;

    @Enumerated(EnumType.STRING)
    private Role role = Role.PATIENT;
}


