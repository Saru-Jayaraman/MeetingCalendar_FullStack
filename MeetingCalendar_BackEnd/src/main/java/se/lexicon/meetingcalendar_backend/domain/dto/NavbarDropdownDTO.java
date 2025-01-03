package se.lexicon.meetingcalendar_backend.domain.dto;

public record NavbarDropdownDTO(
        Integer navbarDropdownId,
        String navbarDropdownName,
        String navbarDropdownHref,
        String navbarDropdownIcon
) {

}