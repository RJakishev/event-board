package ee.ruslan.event_board.repository;

import ee.ruslan.event_board.model.Event;
import ee.ruslan.event_board.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    long countByEvent(Event event);

    List<Registration> findByEventId(Long eventId);

    boolean existsByEventIdAndPersonalId(Long eventId, String personalId);
}
