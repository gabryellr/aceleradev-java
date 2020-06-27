package challenge;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Integer> {

    List<Quote> findByActor(String actor);

}
