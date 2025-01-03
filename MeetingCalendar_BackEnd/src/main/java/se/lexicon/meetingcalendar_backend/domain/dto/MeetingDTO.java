package se.lexicon.meetingcalendar_backend.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MeetingDTO(
        Long id,

        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must not exceed 100 characters")
        String title,

        @NotBlank(message = "Date is required")
        @Pattern(
                regexp = "\\d{4}-\\d{2}-\\d{2}",
                message = "Date must be in the format YYYY-MM-DD"
        )
        String date,

        @NotBlank(message = "Start Time is required")
        @Pattern(
                regexp = "(?:[01]\\d|2[0-3]):[0-5]\\d",
                message = "Time must be in the 24Hrs format HH:MM"
        )
        String startTime,

        @NotBlank(message = "End Time is required")
        @Pattern(
                regexp = "(?:[01]\\d|2[0-3]):[0-5]\\d",
                message = "Time must be in the 24Hrs format HH:MM"
        )
        String endTime,

        @NotBlank(message = "Level is required")
        @Size(max = 50, message = "Location must not exceed 50 characters")
        String level,

        @NotBlank(message = "Participant emails are required")
        String participants,

        @NotBlank(message = "Description is required")
        String description
) {

}