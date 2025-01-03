package se.lexicon.meetingcalendar_backend.domain.dto;

public record DashboardDTO(
        Integer dashboardTabId,
        String dashboardTabName,
        String dashboardTabIcon,
        String dashboardTabNavigateUrl
) {

}