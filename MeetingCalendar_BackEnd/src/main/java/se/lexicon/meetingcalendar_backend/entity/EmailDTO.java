package se.lexicon.meetingcalendar_backend.entity;

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
