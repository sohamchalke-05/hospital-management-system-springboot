package com.Landmine_Soft.Hospital_Management_System.controller;


import com.Landmine_Soft.Hospital_Management_System.dto.ApiResponse;
import com.Landmine_Soft.Hospital_Management_System.dto.DoctorProfileRequestDto;
import com.Landmine_Soft.Hospital_Management_System.dto.DoctorResponseDto;
import com.Landmine_Soft.Hospital_Management_System.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/{id}/profile")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> addProfile(
            @PathVariable Long id,
            @RequestBody DoctorProfileRequestDto dto) {

        DoctorResponseDto response =
                doctorService.addOrUpdateProfile(id, dto);

        return ResponseEntity.ok(
                ApiResponse.<DoctorResponseDto>builder()
                        .success(true)
                        .message("Doctor profile saved successfully")
                        .data(response)
                        .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DoctorResponseDto>> getDoctor(
            @PathVariable Long id) {

        DoctorResponseDto response =
                doctorService.getDoctorById(id);

        return ResponseEntity.ok(
                ApiResponse.<DoctorResponseDto>builder()
                        .success(true)
                        .message("Doctor details fetched successfully")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<DoctorResponseDto>>> listDoctors(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Double fees_lt,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<DoctorResponseDto> doctors =
                doctorService.listDoctors(department, fees_lt, page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<DoctorResponseDto>>builder()
                        .success(true)
                        .message("Doctors fetched successfully")
                        .data(doctors)
                        .build()
        );
    }
}

