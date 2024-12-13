package se.lexicon.meetingcalendar_backend.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.lexicon.meetingcalendar_backend.entity.EmailDTO;
import se.lexicon.meetingcalendar_backend.entity.Meeting;

@Service
public class EmailService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String EMAIL_SERVICE_SEND_URL = "http://localhost:9090/api/v1/email";

    public HttpStatusCode sendRegistrationEmail(Meeting meetingEntity) {
        EmailDTO emailDTO = EmailDTO.builder()
                                .to(meetingEntity.getParticipants())
                                .subject(meetingEntity.getTitle())
                                .html("<div style='color: skyblue; text-align: center; font-size: 20px; padding: 20px;'>" +
                                            "<h3 style='color: green'>Welcome, You are invited for the meeting!</h3>" +
                                            "<p>Date: " + meetingEntity.getDate() + "</p>" +
                                            "<p>Time: " + meetingEntity.getStartTime() + "-" + meetingEntity.getEndTime() + "</p>" +
                                            "<h3 style='color: green'>Login to see more details!</h3>" +
                                        "</div>")
                                .build();
        ResponseEntity<EmailDTO> responseEntity = restTemplate.exchange(EMAIL_SERVICE_SEND_URL, HttpMethod.POST, new HttpEntity<>(emailDTO), EmailDTO.class);
        return responseEntity.getStatusCode();
    }
}
