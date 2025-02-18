package ai.ednova.ticket.booking.service.controllers;

import ai.ednova.ticket.booking.service.dtos.requests.ReservationRequest;
import ai.ednova.ticket.booking.service.dtos.responses.ReservationResponse;
import ai.ednova.ticket.booking.service.dtos.responses.wrapper.ResponseWrapper;
import ai.ednova.ticket.booking.service.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reservations")
@RestController
public class ReservationController {
  private final ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping
  public ResponseEntity<ResponseWrapper<ReservationResponse>> create(
      @RequestBody ReservationRequest reservationRequest) {

    ReservationResponse reservationResponse = reservationService.create(reservationRequest);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(ResponseWrapper.success(reservationResponse));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseWrapper<ReservationResponse>> get(@PathVariable("id") String id) {
    ReservationResponse reservationResponse = reservationService.get(id);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(reservationResponse));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ResponseWrapper<ReservationResponse>> update(
      @PathVariable("id") String id, @RequestBody ReservationRequest reservationRequest) {
    ReservationResponse reservationResponse = reservationService.update(id, reservationRequest);
    return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(reservationResponse));
  }
}
