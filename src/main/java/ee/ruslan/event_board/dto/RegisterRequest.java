package ee.ruslan.event_board.dto;


public record RegisterRequest(String firstname, String lastname, String personalId) {
    public RegisterRequest {
        if (firstname == null || firstname.isBlank()) {
            throw new IllegalArgumentException("First name must not be empty");
        }
        if (lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Last name must not be empty");
        }
        if (personalId == null || personalId.isBlank()) {
            throw new IllegalArgumentException("Personal ID must not be empty");
        }
    }
}
