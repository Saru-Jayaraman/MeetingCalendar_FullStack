package se.lexicon.meetingcalendar_emailsenderapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.meetingcalendar_emailsenderapi.domain.entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {
}