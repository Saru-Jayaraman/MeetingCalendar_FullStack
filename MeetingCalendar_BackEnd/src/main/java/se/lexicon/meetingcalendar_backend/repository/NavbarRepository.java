package se.lexicon.meetingcalendar_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.lexicon.meetingcalendar_backend.domain.entity.Navbar;

@Repository
public interface NavbarRepository extends JpaRepository<Navbar, Integer> {

}