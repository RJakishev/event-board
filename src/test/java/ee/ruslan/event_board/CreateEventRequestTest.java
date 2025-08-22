package ee.ruslan.event_board;

import ee.ruslan.event_board.dto.CreateEventRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CreateEventRequestTest {
    @Test
    void shouldCreateRequestWhenFieldsAreValid() {
        LocalDateTime futureTime = LocalDateTime.now().plusDays(1);
        CreateEventRequest request = new CreateEventRequest("Test Event", futureTime, 100);
        assertNotNull(request);
        assertEquals("Test Event", request.name());
        assertEquals(futureTime, request.date());
        assertEquals(100, request.maxPeople());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        LocalDateTime futureTime = LocalDateTime.now().plusDays(1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CreateEventRequest(" ", futureTime, 100));
        assertEquals("Event name must not be empty", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTimeIsNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CreateEventRequest("Test Event", null, 100));
        assertEquals("Event date must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTimeIsInPast() {
        LocalDateTime pastTime = LocalDateTime.now().minusDays(1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CreateEventRequest("Past Event", pastTime, 50));
        assertEquals("Event date must be in the future", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMaxPeopleIsNotPositive() {
        LocalDateTime futureTime = LocalDateTime.now().plusDays(1);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new CreateEventRequest("Test Event", futureTime, 0));
        assertEquals("Max people must be greater than zero", ex.getMessage());
    }
}
