package ai.ednova.ticket.booking.service.repositories.base;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Filter;
import org.springframework.data.domain.Page;

public interface BaseMongoRepository<T, ID> {
  T save(T t);

  Collection<T> insertAll(Collection<T> t);

  Optional<T> findById(ID id);

  List<T> findByIdIn(Iterable<ID> ids);

  List<T> findAll();

  boolean deleteById(ID id);

  boolean existsById(ID id);

  long count();

  Page<T> filterDocuments(Filter filter);
}
