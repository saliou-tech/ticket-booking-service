package ai.ednova.ticket.booking.service.repositories.base.impl;

import ai.ednova.ticket.booking.service.constants.MongoConstants;
import ai.ednova.ticket.booking.service.enums.DocumentStatus;
import ai.ednova.ticket.booking.service.repositories.base.BaseMongoRepository;
import com.mongodb.client.result.UpdateResult;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class AbstractBaseMongoRepository<T, D> implements BaseMongoRepository<T, D> {

  protected final MongoTemplate mongoTemplate;

  public AbstractBaseMongoRepository(MongoTemplate mongoTemplate, Class<T> entityClass) {
    this.mongoTemplate = mongoTemplate;
    this.entityClass = entityClass;
  }

  protected final Class<T> entityClass;

  @Override
  public T save(T t) {
    return mongoTemplate.save(t);
  }

  @Override
  public Collection<T> insertAll(Collection<T> t) {
    return mongoTemplate.insertAll(t);
  }

  @Override
  public Optional<T> findById(D d) {
    Criteria criteria =
        Criteria.where(MongoConstants.STATUS)
            .is(DocumentStatus.ACTIVE)
            .and(MongoConstants.ID)
            .is(d);
    Query query = new Query(criteria);
    return Optional.ofNullable(mongoTemplate.findOne(query, entityClass));
  }

  @Override
  public List<T> findByIdIn(Iterable<D> ds) {
    return List.of();
  }

  @Override
  public List<T> findAll() {
    return List.of();
  }

  @Override
  public boolean deleteById(D d) {
    Update update = Update.update(MongoConstants.STATUS, DocumentStatus.INACTIVE);
    Criteria criteria =
        Criteria.where(MongoConstants.ID)
            .is(d)
            .and(MongoConstants.STATUS)
            .is(DocumentStatus.ACTIVE);
    Query query = new Query(criteria);
    UpdateResult updateResult = mongoTemplate.updateFirst(query, update, entityClass);
    return updateResult.getModifiedCount() > 0;
  }

  @Override
  public boolean existsById(D d) {
    return false;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public Page<T> filterDocuments(Filter filter) {
    return null;
  }
}
