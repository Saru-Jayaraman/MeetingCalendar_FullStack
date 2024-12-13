package se.lexicon.meetingcalendar_emailsenderapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.lexicon.meetingcalendar_emailsenderapi.domain.dto.EmailDTO;
import se.lexicon.meetingcalendar_emailsenderapi.service.EmailService;

@RequestMapping("/api/v1/email")
@RestController
public class EmailController {

    EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "Get the input of the email content as form and Send it using Gmail account.",
            description = "Used the necessary mail configuration like JavaMailSender and MimeMessage to send email from a Gmail account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Email is sent successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid input.")
    })
    @PostMapping
    public ResponseEntity<Void> doSendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        emailService.sendEmail(emailDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
