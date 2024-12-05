package se.lexicon.meetingcalendar_backend.entity;

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
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String date;
    private String startTime;
    private String endTime;
    private String level;
    private String participants;
    private String description;

    public Meeting(String title, String date, String startTime, String endTime, String level, String participants, String description) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.level = level;
        this.participants = participants;
        this.description = description;
    }
}