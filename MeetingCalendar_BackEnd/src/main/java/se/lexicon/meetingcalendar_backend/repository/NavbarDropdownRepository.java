package se.lexicon.meetingcalendar_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.meetingcalendar_backend.domain.entity.NavbarDropdown;

@Repository
public interface NavbarDropdownRepository extends JpaRepository<NavbarDropdown, Integer> {

}