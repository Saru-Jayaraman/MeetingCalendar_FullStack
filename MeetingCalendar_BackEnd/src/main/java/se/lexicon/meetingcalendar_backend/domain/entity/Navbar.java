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
public class Navbar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer navbarId;
    private String navbarName;
    private String navbarUrl;

    public Navbar(String navbarName, String navbarUrl) {
        this.navbarName = navbarName;
        this.navbarUrl = navbarUrl;
    }
}