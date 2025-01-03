package se.lexicon.meetingcalendar_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.meetingcalendar_backend.domain.dto.DashboardDTO;
import se.lexicon.meetingcalendar_backend.domain.entity.Dashboard;
import se.lexicon.meetingcalendar_backend.repository.DashboardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    @Autowired
    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public DashboardDTO convertToDTO(Dashboard entity) {
        return new DashboardDTO(entity.getDashboardTabId(),
                                    entity.getDashboardTabName(),
                                    entity.getDashboardTabIcon(),
                                    entity.getDashboardTabNavigateUrl()
                                );
    }

    public List<DashboardDTO> getAllDashboardTabs() {
        return dashboardRepository
                    .findAll()
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }
}