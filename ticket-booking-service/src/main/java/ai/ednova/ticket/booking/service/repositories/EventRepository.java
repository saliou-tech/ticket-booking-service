package ai.ednova.ticket.booking.service.repositories;

import ai.ednova.ticket.booking.service.entities.Event;
import ai.ednova.ticket.booking.service.repositories.base.BaseMongoRepository;

public interface EventRepository extends BaseMongoRepository<Event, String> {}
