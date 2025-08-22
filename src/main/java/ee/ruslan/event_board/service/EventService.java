package ee.ruslan.event_board.service;

import ee.ruslan.event_board.dto.CreateEventRequest;
import ee.ruslan.event_board.model.Event;
import ee.ruslan.event_board.repository.EventRepository;
import ee.ruslan.event_board.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Page<Event> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Transactional
    public Event createEvent(CreateEventRequest req) {
        Event e = new Event();
        e.setName(req.name());
        e.setDate(req.date());
        e.setMaxPeople(req.maxPeople());
        return eventRepository.save(e);
    }

    public Optional<Event> getById(Long eventId) {
        return eventRepository.findById(eventId);
    }
}
