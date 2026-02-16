package com.Landmine_Soft.Hospital_Management_System.service;


import com.Landmine_Soft.Hospital_Management_System.dto.AuthResponseDto;
import com.Landmine_Soft.Hospital_Management_System.entity.Doctor;
import com.Landmine_Soft.Hospital_Management_System.entity.Patient;
import com.Landmine_Soft.Hospital_Management_System.entity.enums.Role;
import com.Landmine_Soft.Hospital_Management_System.repository.DoctorRepository;
import com.Landmine_Soft.Hospital_Management_System.repository.PatientRepository;
import com.Landmine_Soft.Hospital_Management_System.security.CustomUserDetailsService;
import com.Landmine_Soft.Hospital_Management_System.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;


    public AuthResponseDto registerPatient(Patient patient) {

        if (patientRepo.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patient.setRole(Role.PATIENT);

        patientRepo.save(patient);

        String token = jwtService.generateToken(patient.getEmail(), "PATIENT");

        return AuthResponseDto.builder()
                .jwtToken(token)
                .role("PATIENT")
                .build();
    }

    public AuthResponseDto registerDoctor(Doctor doctor) {

        if (doctorRepo.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctor.setRole(Role.DOCTOR);

        doctorRepo.save(doctor);
        String token = jwtService.generateToken(doctor.getEmail(), "DOCTOR");


        return AuthResponseDto.builder()
                .role("Doctor")
                .jwtToken(token)
                .build();
    }

    public AuthResponseDto login(String email, String password) {

        var userDetails = userDetailsService.loadUserByUsername(email);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String role = userDetails.getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .replace("ROLE_", "");

        String token = jwtService.generateToken(email, role);

        return AuthResponseDto.builder()
                .jwtToken(token)
                .role(role)
                .build();
    }

}

