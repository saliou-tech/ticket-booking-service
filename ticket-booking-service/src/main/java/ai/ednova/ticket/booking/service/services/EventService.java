package ai.ednova.ticket.booking.service.services;

import ai.ednova.ticket.booking.service.dtos.requests.EventRequest;
import ai.ednova.ticket.booking.service.dtos.requests.ReservationRequest;
import ai.ednova.ticket.booking.service.dtos.responses.EventResponse;
import ai.ednova.ticket.booking.service.dtos.responses.ReservationResponse;

public interface EventService {
  EventResponse create(EventRequest eventRequest);

  EventResponse get(String id);

  EventResponse update(String id, EventRequest eventRequest);

  ReservationResponse bookSeats(String eventId, ReservationRequest reservationRequest);
}
