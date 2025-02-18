package ai.ednova.ticket.booking.service.controllers;

import ai.ednova.ticket.booking.service.dtos.requests.EventRequest;
import ai.ednova.ticket.booking.service.dtos.requests.ReservationRequest;
import ai.ednova.ticket.booking.service.dtos.responses.EventResponse;
import ai.ednova.ticket.booking.service.dtos.responses.ReservationResponse;
import ai.ednova.ticket.booking.service.dtos.responses.wrapper.ResponseWrapper;
import ai.ednova.ticket.booking.service.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/events")
@RestController
public class EventController {
  private final EventService eventService;

  public EventController(EventService eventService) {
    this.eventService = eventService;
  }

  @PostMapping
  public ResponseEntity<ResponseWrapper<EventResponse>> create(
      @RequestBody EventRequest eventRequest) {

    EventResponse eventResponse = eventService.create(eventRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper.success(eventResponse));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseWrapper<EventResponse>> get(@PathVariable("id") String id) {
    EventResponse eventResponse = eventService.get(id);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(eventResponse));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseWrapper<EventResponse>> update(
      @PathVariable("id") String id, @RequestBody EventRequest eventRequest) {
    EventResponse eventResponse = eventService.update(id, eventRequest);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(eventResponse));
  }

  // EventController.java
  @PostMapping("/{eventId}/book")
  public ResponseEntity<ReservationResponse> bookSeats(
      @PathVariable String eventId, @RequestBody ReservationRequest reservationRequest) {

    ReservationResponse reservationResponse = eventService.bookSeats(eventId, reservationRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(reservationResponse);
  }
}
