package se.lexicon.meetingcalendar_backend.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmailDTO {
    private String to;
    private String subject;
    private String html;
}