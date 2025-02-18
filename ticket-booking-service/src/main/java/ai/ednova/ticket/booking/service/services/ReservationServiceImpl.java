package ai.ednova.ticket.booking.service.services;

import ai.ednova.ticket.booking.service.dtos.requests.ReservationRequest;
import ai.ednova.ticket.booking.service.dtos.responses.ReservationResponse;
import ai.ednova.ticket.booking.service.dtos.responses.wrapper.ErrorResponse;
import ai.ednova.ticket.booking.service.entities.Ticket;
import ai.ednova.ticket.booking.service.enums.ErrorCode;
import ai.ednova.ticket.booking.service.exceptions.RestException;
import ai.ednova.ticket.booking.service.mappings.ReservationTansformer;
import ai.ednova.ticket.booking.service.repositories.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
  private final ReservationTansformer reservationTansformer;
  private final ReservationRepository reservationRepository;

  public ReservationServiceImpl(
      ReservationTansformer reservationTansformer, ReservationRepository reservationRepository) {
    this.reservationTansformer = reservationTansformer;
    this.reservationRepository = reservationRepository;
  }

  @Override
  public ReservationResponse create(ReservationRequest reservationRequest) {
    Ticket reservation = reservationTansformer.toReservation(reservationRequest);
    Ticket saveReservation = reservationRepository.save(reservation);

    return reservationTansformer.toReservationResponse(saveReservation);
  }

  @Override
  public ReservationResponse get(String id) {
    Ticket reservation =
        reservationRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new RestException(
                        HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.NOT_FOUND)));
    return reservationTansformer.toReservationResponse(reservation);
  }

  @Override
  public ReservationResponse update(String id, ReservationRequest reservationRequest) {
    Ticket reservation =
        reservationRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new RestException(
                        HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.NOT_FOUND)));
    reservation.setUserId(reservationRequest.getUserId());
    reservation.setEventId(reservationRequest.getEventId());
    reservation.setSeatNumber(reservationRequest.getSeatNumber());
    Ticket saveReservation = reservationRepository.save(reservation);
    return reservationTansformer.toReservationResponse(saveReservation);
  }
}
