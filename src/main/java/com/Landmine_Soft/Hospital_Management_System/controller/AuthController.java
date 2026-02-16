package com.Landmine_Soft.Hospital_Management_System.controller;


import com.Landmine_Soft.Hospital_Management_System.dto.ApiResponse;
import com.Landmine_Soft.Hospital_Management_System.dto.AuthResponseDto;
import com.Landmine_Soft.Hospital_Management_System.dto.LoginRequest;
import com.Landmine_Soft.Hospital_Management_System.entity.Doctor;
import com.Landmine_Soft.Hospital_Management_System.entity.Patient;
import com.Landmine_Soft.Hospital_Management_System.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/patient/register")
    public ResponseEntity<ApiResponse<AuthResponseDto>> registerPatient(
            @Valid @RequestBody Patient patient) {

        AuthResponseDto response = authService.registerPatient(patient);

        return ResponseEntity.ok(
                ApiResponse.<AuthResponseDto>builder()
                        .success(true)
                        .message("Patient registered successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/doctor/register")
    public ResponseEntity<ApiResponse<AuthResponseDto>> registerDoctor(
            @Valid @RequestBody Doctor doctor) {

        AuthResponseDto response = authService.registerDoctor(doctor);

        return ResponseEntity.ok(
                ApiResponse.<AuthResponseDto>builder()
                        .success(true)
                        .message("Doctor registered successfully")
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDto>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponseDto response = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(
                ApiResponse.<AuthResponseDto>builder()
                        .success(true)
                        .message("Login successful")
                        .data(response)
                        .build()
        );
    }

}

