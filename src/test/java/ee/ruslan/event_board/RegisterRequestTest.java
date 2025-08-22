package ee.ruslan.event_board;

import ee.ruslan.event_board.dto.RegisterRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterRequestTest {

    @Test
    void shouldCreateRequestWhenFieldsAreValid() {
        RegisterRequest request = new RegisterRequest("John", "Doe", "123456789");
        assertNotNull(request);
        assertEquals("John", request.firstname());
        assertEquals("Doe", request.lastname());
        assertEquals("123456789", request.personalId());
    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsBlank() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new RegisterRequest(" ", "Doe", "123456789"));
        assertEquals("First name must not be empty", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsBlank() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new RegisterRequest("John", " ", "123456789"));
        assertEquals("Last name must not be empty", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenPersonalIdIsBlank() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> new RegisterRequest("John", "Doe", " "));
        assertEquals("Personal ID must not be empty", ex.getMessage());
    }
}
