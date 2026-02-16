package com.Landmine_Soft.Hospital_Management_System.controller;


import com.Landmine_Soft.Hospital_Management_System.dto.ApiResponse;
import com.Landmine_Soft.Hospital_Management_System.dto.PatientResponseDto;
import com.Landmine_Soft.Hospital_Management_System.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/patients")
    public ResponseEntity<ApiResponse<Page<PatientResponseDto>>> getPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String city) {

        Page<PatientResponseDto> patients =
                adminService.getAllPatients(page, size, city);

        return ResponseEntity.ok(
                ApiResponse.<Page<PatientResponseDto>>builder()
                        .success(true)
                        .message("Patients fetched successfully")
                        .data(patients)
                        .build()
        );
    }

}

