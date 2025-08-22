package ee.ruslan.event_board;

import ee.ruslan.event_board.dto.CreateEventRequest;
import ee.ruslan.event_board.model.Event;
import ee.ruslan.event_board.repository.EventRepository;
import ee.ruslan.event_board.repository.RegistrationRepository;
import ee.ruslan.event_board.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        Event event = new Event(null, "Test Event", LocalDateTime.now().plusDays(1), 10);
        CreateEventRequest eventRequest = new CreateEventRequest(event.getName(), event.getDate(), event.getMaxPeople());
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        Event created = eventService.createEvent(eventRequest);
        assertEquals("Test Event", created.getName());
    }

    @Test
    void testGetEvents() {
        PageRequest pageable = PageRequest.of(1, 2);
        Event event = new Event(1L, "E1", LocalDateTime.now().plusDays(1), 5);
        Page<Event> page = new PageImpl<>(List.of(event), pageable, 1);

        when(eventRepository.findAll(pageable)).thenReturn(page);

        Page<Event> events = eventService.getEvents(pageable);

        assertEquals(1, events.getContent().size());
        assertEquals("E1", events.getContent().get(0).getName());
    }

}
