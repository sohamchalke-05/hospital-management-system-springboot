package com.Landmine_Soft.Hospital_Management_System.controller;


import com.Landmine_Soft.Hospital_Management_System.dto.ApiResponse;
import com.Landmine_Soft.Hospital_Management_System.dto.AvailabilityRequestDto;
import com.Landmine_Soft.Hospital_Management_System.dto.SlotResponseDto;
import com.Landmine_Soft.Hospital_Management_System.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping("/{id}/availability")
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse<String>> addAvailability(
            @PathVariable Long id,
            @RequestBody List<AvailabilityRequestDto> requestList) {

        availabilityService.addAvailability(id, requestList);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Availability added successfully")
                        .data(null)
                        .build()
        );
    }



    @GetMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<List<SlotResponseDto>>> getSlots(
            @PathVariable Long id,
            @RequestParam LocalDate date) {

        List<SlotResponseDto> slots =
                availabilityService.getSlots(id, date);

        return ResponseEntity.ok(
                ApiResponse.<List<SlotResponseDto>>builder()
                        .success(true)
                        .message("Slots fetched successfully")
                        .data(slots)
                        .build()
        );
    }
}

