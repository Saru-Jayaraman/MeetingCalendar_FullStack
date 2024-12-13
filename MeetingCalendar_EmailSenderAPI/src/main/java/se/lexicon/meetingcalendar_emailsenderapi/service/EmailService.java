package se.lexicon.meetingcalendar_emailsenderapi.service;


import se.lexicon.meetingcalendar_emailsenderapi.domain.dto.EmailDTO;

public interface EmailService {
    void sendEmail(EmailDTO dto);
}