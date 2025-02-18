package ai.ednova.ticket.booking.service.services;

import ai.ednova.ticket.booking.service.dtos.requests.EventRequest;
import ai.ednova.ticket.booking.service.dtos.requests.ReservationRequest;
import ai.ednova.ticket.booking.service.dtos.responses.EventResponse;
import ai.ednova.ticket.booking.service.dtos.responses.ReservationResponse;
import ai.ednova.ticket.booking.service.dtos.responses.wrapper.ErrorResponse;
import ai.ednova.ticket.booking.service.entities.Event;
import ai.ednova.ticket.booking.service.enums.ErrorCode;
import ai.ednova.ticket.booking.service.exceptions.RestException;
import ai.ednova.ticket.booking.service.mappings.EventTansformer;
import ai.ednova.ticket.booking.service.repositories.EventRepository;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {
  private final EventTansformer eventTansformer;
  private final EventRepository eventRepository;
  private final RedisTemplate<String, String> redisTemplate;
  private final RedissonClient redissonClient;

  public EventServiceImpl(
      EventTansformer eventTansformer,
      EventRepository eventRepository,
      RedisTemplate<String, String> redisTemplate,
      RedissonClient redissonClient) {
    this.eventTansformer = eventTansformer;
    this.eventRepository = eventRepository;
    this.redisTemplate = redisTemplate;
    this.redissonClient = redissonClient;
  }

  @Override
  public EventResponse create(EventRequest eventRequest) {
    Event event = eventTansformer.toEvent(eventRequest);
    Event saveEvent = eventRepository.save(event);
    return eventTansformer.toEventResponse(saveEvent);
  }

  @Override
  public EventResponse get(String id) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new RestException(
                        HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.NOT_FOUND)));
    return eventTansformer.toEventResponse(event);
  }

  @Override
  public EventResponse update(String id, EventRequest eventRequest) {
    Event event =
        eventRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new RestException(
                        HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.NOT_FOUND)));
    Event updateEvent = eventTansformer.toEvent(eventRequest);
    updateEvent.setId(UUID.fromString(id));
    Event saveEvent = eventRepository.save(updateEvent);
    return eventTansformer.toEventResponse(saveEvent);
  }

  @Override
  public ReservationResponse bookSeats(String eventId, ReservationRequest reservationRequest) {
    // Use Redis lock to ensure only one booking operation happens at a time
    RLock lock = redissonClient.getLock("event_lock_" + eventId);
    lock.lock();
    try {
      Event event =
          eventRepository
              .findById(eventId)
              .orElseThrow(
                  () ->
                      new RestException(
                          HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.NOT_FOUND)));

      if (event.getAvailableSeats() < reservationRequest.getSeatNumber()) {
        throw new RestException(
            HttpStatus.BAD_REQUEST, ErrorResponse.from(ErrorCode.INSUFFICIENT_SEATS_AVAILABLE));
      }

      // Reserve seats temporarily in Redis (TTL of 10 minutes)
      String reservationKey = "reservation:" + eventId + ":" + reservationRequest.getUserId();
      saveTemporaryReservation(
          reservationKey, reservationRequest.getSeatNumber(), 10, TimeUnit.MINUTES);

      // Decrement available seats in MongoDB
      event.setAvailableSeats(event.getAvailableSeats() - reservationRequest.getSeatNumber());
      eventRepository.save(event);

      return new ReservationResponse(
          event.getId(), reservationRequest.getUserId(), reservationRequest.getSeatNumber());
    } finally {
      lock.unlock();
    }
  }

  public void saveTemporaryReservation(String key, Integer seatCount, long ttl, TimeUnit timeUnit) {
    redisTemplate.opsForValue().set(key, seatCount.toString(), ttl, timeUnit);
  }

  // Get temporary reservation from Redis
  public Integer getTemporaryReservation(String key) {
    String value = redisTemplate.opsForValue().get(key);
    return value != null ? Integer.valueOf(value) : null;
  }
}
