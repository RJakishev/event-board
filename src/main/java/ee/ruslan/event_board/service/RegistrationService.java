package ee.ruslan.event_board.service;

import ee.ruslan.event_board.dto.RegisterRequest;
import ee.ruslan.event_board.model.Event;
import ee.ruslan.event_board.model.Registration;
import ee.ruslan.event_board.repository.EventRepository;
import ee.ruslan.event_board.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private EventRepository eventRepository;

    public Long getRegistrationCount(Event event) {
        if (event == null) {
            throw new RuntimeException("Event is null");
        }
        return registrationRepository.countByEvent(event);
    }

    private Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }

    public List<Registration> getAllregistrationsByEventId(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    @Transactional
    public Registration register(Long eventId, RegisterRequest request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        if (registrationRepository.existsByEventIdAndPersonalId(eventId, request.personalId())) {
            throw new IllegalStateException("User already registered for this event");
        }

        long currentPeopleCount = getRegistrationCount(event);
        if (currentPeopleCount >= event.getMaxPeople()) {
            throw new IllegalStateException("Event is full");
        }

        return save(createRegistration(request, event));
    }

    private Registration createRegistration(RegisterRequest request, Event event) {
        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setFirstName(request.firstname());
        registration.setLastName(request.lastname());
        registration.setPersonalId(request.personalId());
        return registration;
    }
}
