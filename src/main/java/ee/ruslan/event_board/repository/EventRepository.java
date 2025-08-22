package ee.ruslan.event_board.repository;

import ee.ruslan.event_board.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event>, PagingAndSortingRepository<Event, Long> {
    Optional<Event> findById(long id);
}

