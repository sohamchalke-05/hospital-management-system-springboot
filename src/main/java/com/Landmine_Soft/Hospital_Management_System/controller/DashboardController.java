package com.Landmine_Soft.Hospital_Management_System.controller;

import com.Landmine_Soft.Hospital_Management_System.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute("title", "Dashboard");
        model.addAttribute("content", "dashboard :: content");

        model.addAttribute("totalPatients", patientRepository.count());
        model.addAttribute("totalDoctors", 25);
        model.addAttribute("appointmentsToday", 12);
        model.addAttribute("availableBeds", 8);

        return "layout";
    }

}
