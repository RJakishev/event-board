package ee.ruslan.event_board.util;

import ee.ruslan.event_board.dto.EventDTO;
import ee.ruslan.event_board.model.Event;
import ee.ruslan.event_board.service.EventService;
import ee.ruslan.event_board.service.RegistrationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoFactory {
    ModelMapper modelMapper = new ModelMapper();
    TypeMap<Event, EventDTO> propertyMapper = this.modelMapper.createTypeMap(Event.class, EventDTO.class);
    @Autowired
    private EventService eventService;
    @Autowired
    private RegistrationService registrationService;

    public EventDTO getDTO(Event event) {
        if (event == null) return null;
        EventDTO dto = modelMapper.map(event, EventDTO.class);
        return dto;
    }

    public Page<EventDTO> getDTOList(Page<Event> eventPage) {
        if (eventPage == null) return null;

        List<Event> entities = eventPage.getContent();
        List<EventDTO> dtos = getDTOList(entities);

        PageRequest pageable = PageRequest.of(eventPage.getNumber(), eventPage.getSize());
        return new PageImpl<>(dtos, pageable, eventPage.getTotalElements());
    }

    public List<EventDTO> getDTOList(List<Event> events) {
        List<EventDTO> dtos = events.stream()
                .map(
                        (element) -> modelMapper.map(element, EventDTO.class)
                )
                .collect(Collectors.toList());

        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            int maxPeople = event.getMaxPeople();
            long currentPeopleCount = registrationService.getRegistrationCount(event);
            dtos.get(i).setIsActive(maxPeople > currentPeopleCount);
        }
        return dtos;
    }
}
