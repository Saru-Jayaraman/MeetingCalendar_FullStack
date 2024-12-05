package se.lexicon.meetingcalendar_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.meetingcalendar_backend.entity.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}