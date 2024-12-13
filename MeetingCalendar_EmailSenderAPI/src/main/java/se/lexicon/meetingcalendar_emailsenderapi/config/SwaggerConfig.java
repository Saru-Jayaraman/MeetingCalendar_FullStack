package se.lexicon.meetingcalendar_emailsenderapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Sends email from Gmail account", version = "0.1", description = "Send an email from Gmail account using SMTP protocol."))
public class SwaggerConfig {

}