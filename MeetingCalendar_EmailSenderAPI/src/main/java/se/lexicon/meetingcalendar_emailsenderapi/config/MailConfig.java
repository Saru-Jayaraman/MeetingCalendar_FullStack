package se.lexicon.meetingcalendar_emailsenderapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    EmailProperties emailProperties;

    @Autowired
    public MailConfig(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(emailProperties.getHost());
        javaMailSender.setPort(emailProperties.getPort());
        javaMailSender.setUsername(emailProperties.getUsername());
        javaMailSender.setPassword(emailProperties.getPassword());
        javaMailSender.getJavaMailProperties().put("mail.smtp.auth", emailProperties.isSmtpAuth());
        javaMailSender.getJavaMailProperties().put("mail.smtp.starttls.enable", emailProperties.isStartTls());
        return javaMailSender;
    }
}