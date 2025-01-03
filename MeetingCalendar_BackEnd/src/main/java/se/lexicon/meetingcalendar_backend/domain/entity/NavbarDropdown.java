package se.lexicon.meetingcalendar_backend.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NavbarDropdown {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer navbarDropdownId;
    private String navbarDropdownName;
    private String navbarDropdownHref;
    private String navbarDropdownIcon;

    public NavbarDropdown(String navbarDropdownName, String navbarDropdownHref, String navbarDropdownIcon) {
        this.navbarDropdownName = navbarDropdownName;
        this.navbarDropdownHref = navbarDropdownHref;
        this.navbarDropdownIcon = navbarDropdownIcon;
    }
}