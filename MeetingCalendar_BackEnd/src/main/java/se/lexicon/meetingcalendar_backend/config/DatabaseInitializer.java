package se.lexicon.meetingcalendar_backend.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import se.lexicon.meetingcalendar_backend.entity.Meeting;
import se.lexicon.meetingcalendar_backend.repository.MeetingRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer {
    private final MeetingRepository meetingRepository;

    public DatabaseInitializer(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        List<Meeting> meetings = Arrays.asList(
                new Meeting("Team Meeting", "2024-12-06", "10:00", "11:00", "Team", "test1@gmail.com, test2@gmail.com", "Requirement discussion"),
                new Meeting("Review Meeting", "2024-12-07", "13:00", "14:00", "Team", "test1@gmail.com", "Review the user story"),
                new Meeting("Monthly Meeting", "2024-12-08", "09:00", "10:00", "Account", "test1@gmail.com, test2@gmail.com, test3@gmail.com, test4@gmail.com", "Monthly meetup")
        );
        meetingRepository.saveAll(meetings);
        System.out.println("Database initialized...");
    }
}