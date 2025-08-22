package ee.ruslan.event_board.controller;


import ee.ruslan.event_board.dto.CreateEventRequest;
import ee.ruslan.event_board.dto.EventDTO;
import ee.ruslan.event_board.dto.RegisterRequest;
import ee.ruslan.event_board.model.Event;
import ee.ruslan.event_board.model.Registration;
import ee.ruslan.event_board.service.EventService;
import ee.ruslan.event_board.service.RegistrationService;
import ee.ruslan.event_board.util.DtoFactory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ee.ruslan.event_board.security.SecurityUtil.isLoggedInUser;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private DtoFactory dtoFactory;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(Pageable pageable) {
        Page<Event> events = eventService.getEvents(pageable);
        return ResponseEntity.ok(dtoFactory.getDTOList(events));
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody @Valid CreateEventRequest req) {
        if (isLoggedInUser()) return ResponseEntity.ok(eventService.createEvent(req));
        else return null;
    }

    @PostMapping("/{eventId}/register")
    public ResponseEntity<?> register(@PathVariable Long eventId, @RequestBody @Valid RegisterRequest req) {
        try {
            Registration r = registrationService.register(eventId, req);
            return ResponseEntity.ok(r);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(java.util.Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(java.util.Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{eventId}/registrations")
    public List<Registration> registrations(@PathVariable Long eventId) {
        return registrationService.getAllregistrationsByEventId(eventId);
    }
}
