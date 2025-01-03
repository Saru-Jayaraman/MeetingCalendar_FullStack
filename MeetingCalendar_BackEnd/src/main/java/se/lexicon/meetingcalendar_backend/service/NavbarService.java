package se.lexicon.meetingcalendar_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.meetingcalendar_backend.domain.dto.NavbarDTO;
import se.lexicon.meetingcalendar_backend.domain.dto.NavbarDropdownDTO;
import se.lexicon.meetingcalendar_backend.domain.entity.Navbar;
import se.lexicon.meetingcalendar_backend.domain.entity.NavbarDropdown;
import se.lexicon.meetingcalendar_backend.repository.NavbarDropdownRepository;
import se.lexicon.meetingcalendar_backend.repository.NavbarRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NavbarService {
    private final NavbarRepository navbarRepository;
    private final NavbarDropdownRepository navbarDropdownRepository;

    @Autowired
    public NavbarService(NavbarRepository navbarRepository, NavbarDropdownRepository navbarDropdownRepository) {
        this.navbarRepository = navbarRepository;
        this.navbarDropdownRepository = navbarDropdownRepository;
    }

    public NavbarDTO convertToNavbarDTO(Navbar entity) {
        return new NavbarDTO(entity.getNavbarId(),
                            entity.getNavbarName(),
                            entity.getNavbarUrl()
        );
    }

    public NavbarDropdownDTO convertToNavbarDropdownDTO(NavbarDropdown entity) {
        return new NavbarDropdownDTO(entity.getNavbarDropdownId(),
                entity.getNavbarDropdownName(),
                entity.getNavbarDropdownHref(),
                entity.getNavbarDropdownIcon()
        );
    }

    public List<NavbarDTO> getAllNavbarTab() {
        return navbarRepository
                .findAll()
                .stream()
                .map(this::convertToNavbarDTO)
                .collect(Collectors.toList());
    }

    public List<NavbarDropdownDTO> getAllNavbarDropdownTab() {
        return navbarDropdownRepository
                .findAll()
                .stream()
                .map(this::convertToNavbarDropdownDTO)
                .collect(Collectors.toList());
    }
}