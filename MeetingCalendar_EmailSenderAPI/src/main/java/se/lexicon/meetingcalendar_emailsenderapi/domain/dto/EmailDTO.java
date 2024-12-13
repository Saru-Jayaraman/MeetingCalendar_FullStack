package se.lexicon.meetingcalendar_emailsenderapi.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmailDTO {
    @NotBlank(message = "To Email field is required")
    @Email(message = "Invalid Email format")
    private String to;

    @NotBlank(message = "Subject field is required")
    private String subject;

    @NotBlank(message = "Html field is required")
    private String html;

    @Positive(message = "Type field should be a positive number")
    private Integer type;
}