package se.lexicon.meetingcalendar_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.meetingcalendar_backend.domain.dto.NavbarDTO;
import se.lexicon.meetingcalendar_backend.domain.dto.NavbarDropdownDTO;
import se.lexicon.meetingcalendar_backend.service.NavbarService;

import java.util.List;

@RestController
@RequestMapping("/api/navbar")
@CrossOrigin("*")
public class NavbarController {
    private final NavbarService navbarService;

    @Autowired
    public NavbarController(NavbarService navbarService) {
        this.navbarService = navbarService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NavbarDTO>> getAllNavbarTabs() {
        return ResponseEntity.status(HttpStatus.OK).body(navbarService.getAllNavbarTab());
    }

    @GetMapping("/dropdown")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<NavbarDropdownDTO>> getAllNavbarDropdownTabs() {
        return ResponseEntity.status(HttpStatus.OK).body(navbarService.getAllNavbarDropdownTab());
    }
}