package se.lexicon.meetingcalendar_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.meetingcalendar_backend.dto.MeetingDTO;
import se.lexicon.meetingcalendar_backend.service.MeetingService;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@CrossOrigin("*")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MeetingDTO>> getAllMeetings() {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.getAllMeetings());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MeetingDTO> getMeetingById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.findMeetingById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MeetingDTO> createMeeting(@RequestBody MeetingDTO dto) {
        MeetingDTO savedDto = meetingService.saveMeeting(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateMeeting(@PathVariable Long id, @RequestBody MeetingDTO dto) {
        meetingService.updateMeeting(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.noContent().build();
    }
}