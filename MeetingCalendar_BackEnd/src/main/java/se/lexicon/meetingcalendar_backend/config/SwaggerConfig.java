package se.lexicon.meetingcalendar_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "se.lexicon")
@OpenAPIDefinition(info = @Info(title = "Meeting Calendar API", version = "0.1", description = "API Information"))
public class SwaggerConfig {

}