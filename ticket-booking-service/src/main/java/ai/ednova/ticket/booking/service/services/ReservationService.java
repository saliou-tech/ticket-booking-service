package ai.ednova.ticket.booking.service.services;

import ai.ednova.ticket.booking.service.dtos.requests.ReservationRequest;
import ai.ednova.ticket.booking.service.dtos.responses.ReservationResponse;

public interface ReservationService {
  ReservationResponse create(ReservationRequest reservationRequest);

  ReservationResponse get(String id);

  ReservationResponse update(String id, ReservationRequest eventRequest);
}
