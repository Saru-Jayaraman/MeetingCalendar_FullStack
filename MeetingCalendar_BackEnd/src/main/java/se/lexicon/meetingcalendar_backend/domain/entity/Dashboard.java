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
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dashboardTabId;
    private String dashboardTabName;
    private String dashboardTabIcon;
    private String dashboardTabNavigateUrl;

    public Dashboard(String dashboardTabName, String dashboardTabIcon, String dashboardTabNavigateUrl) {
        this.dashboardTabName = dashboardTabName;
        this.dashboardTabIcon = dashboardTabIcon;
        this.dashboardTabNavigateUrl = dashboardTabNavigateUrl;
    }
}