package se.lexicon.meetingcalendar_emailsenderapi.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        StringBuilder details = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            details.append(fieldError.getField());
            details.append(": ");
            details.append(fieldError.getDefaultMessage());
            details.append(", ");
        });
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), details.toString());
        return ResponseEntity.status(errorDTO.getErrorCode()).body(errorDTO);
    }

    @ExceptionHandler({IllegalArgumentException.class, EmailException.class})
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex) {
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(errorDTO.getErrorCode()).body(errorDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex) {
        String errorId = UUID.randomUUID().toString();
        System.out.println("Error ID: " + errorId);
        ex.printStackTrace();
        ErrorDTO errorDTO = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Call Support Team. Error Id: " + errorId);
        return ResponseEntity.status(errorDTO.getErrorCode()).body(errorDTO);
    }
}