package ee.ruslan.event_board.dto;

import java.time.LocalDateTime;

public record CreateEventRequest(String name, LocalDateTime date, int maxPeople) {
    public CreateEventRequest {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Event name must not be empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Event date must not be null");
        }
        if (date.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Event date must be in the future");
        }
        if (maxPeople <= 0) {
            throw new IllegalArgumentException("Max people must be greater than zero");
        }
    }
}
