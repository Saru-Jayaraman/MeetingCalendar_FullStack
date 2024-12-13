package se.lexicon.meetingcalendar_backend.service;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import se.lexicon.meetingcalendar_backend.dto.MeetingDTO;
import se.lexicon.meetingcalendar_backend.entity.Meeting;
import se.lexicon.meetingcalendar_backend.exception.EmailServiceFailedException;
import se.lexicon.meetingcalendar_backend.repository.MeetingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final EmailService emailService;

    public MeetingService(MeetingRepository meetingRepository, EmailService emailService) {
        this.meetingRepository = meetingRepository;
        this.emailService = emailService;
    }

    public MeetingDTO convertToDTO(Meeting entity) {
        return new MeetingDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getLevel(),
                entity.getParticipants(),
                entity.getDescription()
        );
    }

    public Meeting convertToEntity(MeetingDTO dto) {
        return new Meeting(
                dto.id(),
                dto.title(),
                dto.date(),
                dto.startTime(),
                dto.endTime(),
                dto.level(),
                dto.participants(),
                dto.description()
        );
    }

    public List<MeetingDTO> getAllMeetings() {
        return meetingRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MeetingDTO findMeetingById(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found for the ID: " + id));
        return convertToDTO(meeting);
    }

    public MeetingDTO saveMeeting(MeetingDTO meeting) {
        Meeting meetingEntity = convertToEntity(meeting);
        Meeting savedMeeting = meetingRepository.save(meetingEntity);
        //todo: Send a Welcome Email when registered a new user
        HttpStatusCode statusCode = emailService.sendRegistrationEmail(meetingEntity);
        if(!statusCode.is2xxSuccessful()) {
            System.out.println("Status code: " + statusCode);
            throw new EmailServiceFailedException("EMAIL NOT SENT!!!");
        }
        return convertToDTO(savedMeeting);
    }

    public void updateMeeting(Long id, MeetingDTO meeting) {
        Meeting foundMeeting = meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting not found for the ID: " + id));
        Meeting entity = convertToEntity(meeting);
        Meeting savedMeeting = meetingRepository.save(entity);
    }

    public void deleteMeeting(Long id) {
        if (!meetingRepository.existsById(id)) {
            throw new RuntimeException("Meeting not found for the ID: " + id);
        }
        meetingRepository.deleteById(id);
    }
}