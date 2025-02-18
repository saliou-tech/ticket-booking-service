package ai.ednova.ticket.booking.service.mappings;

import ai.ednova.ticket.booking.service.dtos.requests.EventRequest;
import ai.ednova.ticket.booking.service.dtos.responses.EventResponse;
import ai.ednova.ticket.booking.service.entities.Event;
import org.springframework.stereotype.Component;

@Component
public class EventTansformer {

  public Event toEvent(EventRequest eventRequest) {
    return Event.builder()
        .name(eventRequest.getName())
        .date(eventRequest.getDate())
        .availableSeats(eventRequest.getTotalSeats())
        .description(eventRequest.getDescription())
        .totalSeats(eventRequest.getTotalSeats())
        .location(eventRequest.getLocation())
        .build();
  }

  public EventResponse toEventResponse(Event event) {
    return EventResponse.builder()
        .id(event.getId())
        .name(event.getName())
        .description(event.getDescription())
        .location(event.getLocation())
        .date(event.getDate())
        .totalSeats(event.getTotalSeats())
        .availableSeats(event.getAvailableSeats())
        .build();
  }
}
