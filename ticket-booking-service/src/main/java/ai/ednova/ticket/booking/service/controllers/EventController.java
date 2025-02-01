package ai.ednova.ticket.booking.service.controllers;

import ai.ednova.ticket.booking.service.dtos.requests.EventRequest;
import ai.ednova.ticket.booking.service.dtos.responses.EventResponse;
import ai.ednova.ticket.booking.service.dtos.responses.wrapper.ResponseWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

  @PostMapping("/events")
  public ResponseEntity<ResponseWrapper<EventResponse>> create(EventRequest eventRequest) {
    return null;
  }
}
