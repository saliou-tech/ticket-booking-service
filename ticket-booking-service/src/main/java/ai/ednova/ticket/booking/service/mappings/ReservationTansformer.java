package ai.ednova.ticket.booking.service.mappings;

import ai.ednova.ticket.booking.service.dtos.requests.ReservationRequest;
import ai.ednova.ticket.booking.service.dtos.responses.ReservationResponse;
import ai.ednova.ticket.booking.service.entities.Ticket;
import org.springframework.stereotype.Component;

@Component
public class ReservationTansformer {
  public Ticket toReservation(ReservationRequest reservationRequest) {
    return Ticket.builder()
        .userId(reservationRequest.getUserId())
        .eventId(reservationRequest.getEventId())
        .seatNumber(reservationRequest.getSeatNumber())
        .build();
  }

  public ReservationResponse toReservationResponse(Ticket saveReservation) {
    return ReservationResponse.builder()
        .userId(saveReservation.getUserId())
        .eventId(saveReservation.getEventId())
        .seatNumber(saveReservation.getSeatNumber())
        .build();
  }
}
