package se.lexicon.meetingcalendar_backend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.meetingcalendar_backend.domain.dto.MeetingDTO;
import se.lexicon.meetingcalendar_backend.service.MeetingService;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@CrossOrigin("*")
@Validated
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
    public ResponseEntity<MeetingDTO> getMeetingById(
            @PathVariable @NotBlank(message = "Meeting ID is required") Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(meetingService.findMeetingById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MeetingDTO> createMeeting(
            @RequestBody @Valid MeetingDTO dto
    ) {
        MeetingDTO savedDto = meetingService.saveMeeting(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> updateMeeting(
            @PathVariable @NotBlank(message = "Meeting ID is required") Long id,
            @RequestBody @Valid MeetingDTO dto
    ) {
        meetingService.updateMeeting(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteMeeting(
            @PathVariable @NotBlank(message = "Meeting ID is required") Long id
    ) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.noContent().build();
    }
}