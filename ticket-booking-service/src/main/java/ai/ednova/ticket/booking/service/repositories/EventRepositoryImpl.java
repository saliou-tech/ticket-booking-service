package ai.ednova.ticket.booking.service.repositories;

import ai.ednova.ticket.booking.service.entities.Event;
import ai.ednova.ticket.booking.service.repositories.base.impl.AbstractBaseMongoRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl extends AbstractBaseMongoRepository<Event, String>
    implements EventRepository {

  public EventRepositoryImpl(MongoTemplate mongoTemplate) {
    super(mongoTemplate, Event.class);
  }
}
