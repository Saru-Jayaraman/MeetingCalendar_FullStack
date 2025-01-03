package se.lexicon.meetingcalendar_backend.initializer;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendar_backend.domain.entity.Dashboard;
import se.lexicon.meetingcalendar_backend.domain.entity.Navbar;
import se.lexicon.meetingcalendar_backend.domain.entity.NavbarDropdown;
import se.lexicon.meetingcalendar_backend.repository.DashboardRepository;
import se.lexicon.meetingcalendar_backend.repository.NavbarDropdownRepository;
import se.lexicon.meetingcalendar_backend.repository.NavbarRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer {
    private final DashboardRepository dashboardRepository;
    private final NavbarRepository navbarRepository;
    private final NavbarDropdownRepository navbarDropdownRepository;

    @Autowired
    public DatabaseInitializer(DashboardRepository dashboardRepository, NavbarRepository navbarRepository, NavbarDropdownRepository navbarDropdownRepository) {
        this.dashboardRepository = dashboardRepository;
        this.navbarRepository = navbarRepository;
        this.navbarDropdownRepository = navbarDropdownRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        List<Dashboard> dashboardList = Arrays.asList(
                new Dashboard("Schedule Meeting", "FaCalendarPlus", "/dashboard/scheduleMeeting"),
                new Dashboard("Manage Meetings", "FaCalendar", "/dashboard/manageMeeting"),
                new Dashboard("Users & Permissions", "FaUsers", "/dashboard/usersAndPermissions"),
                new Dashboard("Notifications", "IoIosNotifications", "/dashboard/notifications"),
                new Dashboard("Analytics", "BsGraphUpArrow", "/dashboard/analytics"),
                new Dashboard("Settings", "IoSettingsSharp", "/dashboard/settings")
        );
        dashboardRepository.saveAll(dashboardList);
        List<Navbar> navbarList = Arrays.asList(
               new Navbar("Home", "/dashboard/home"),
               new Navbar("About", "/about"),
               new Navbar("Services", "/services"),
               new Navbar("Contact", "/contact")
        );
        navbarRepository.saveAll(navbarList);
        List<NavbarDropdown> navbarDropdownList = Arrays.asList(
                new NavbarDropdown("SignOut", "#","VscSignOut"),
                new NavbarDropdown("Settings", "#","MdOutlineSettings")
        );
        navbarDropdownRepository.saveAll(navbarDropdownList);

        System.out.println("Dashboard tab and Navbar tab data's are initialized...");
    }
}