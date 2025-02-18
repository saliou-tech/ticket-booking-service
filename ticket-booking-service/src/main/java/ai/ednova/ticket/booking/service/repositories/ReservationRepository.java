package ai.ednova.ticket.booking.service.repositories;

import ai.ednova.ticket.booking.service.entities.Ticket;
import ai.ednova.ticket.booking.service.repositories.base.BaseMongoRepository;

public interface ReservationRepository extends BaseMongoRepository<Ticket, String> {}
