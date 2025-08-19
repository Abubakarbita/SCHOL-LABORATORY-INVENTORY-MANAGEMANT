package com.example.invproject.controller;

import com.example.invproject.Dto.DashboardReportDTO;
import com.example.invproject.service.DashboardService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/report")
    public DashboardReportDTO getReport() {
        return dashboardService.getDashboardReport();
    }
}
