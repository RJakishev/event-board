package ee.ruslan.event_board.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventDTO {

    private Long id;
    private LocalDateTime date;
    private String name;
    private Boolean isActive;
}
