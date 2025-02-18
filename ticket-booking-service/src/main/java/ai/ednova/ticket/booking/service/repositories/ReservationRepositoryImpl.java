package ai.ednova.ticket.booking.service.repositories;

import ai.ednova.ticket.booking.service.entities.Ticket;
import ai.ednova.ticket.booking.service.repositories.base.impl.AbstractBaseMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepositoryImpl extends AbstractBaseMongoRepository<Ticket, String>
    implements ReservationRepository {

  public ReservationRepositoryImpl(MongoTemplate mongoTemplate) {
    super(mongoTemplate, Ticket.class);
  }
}
