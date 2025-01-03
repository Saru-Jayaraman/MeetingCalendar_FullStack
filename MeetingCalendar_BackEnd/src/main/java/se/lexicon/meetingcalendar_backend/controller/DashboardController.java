package se.lexicon.meetingcalendar_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.meetingcalendar_backend.domain.dto.DashboardDTO;
import se.lexicon.meetingcalendar_backend.service.DashboardService;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {
    private final DashboardService dashboardService;

    @Autowired
    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DashboardDTO>> getAllDashboardTabs() {
        return ResponseEntity.status(HttpStatus.OK).body(dashboardService.getAllDashboardTabs());
    }
}