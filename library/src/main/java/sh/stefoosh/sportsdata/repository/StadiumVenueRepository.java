package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.StadiumVenue;

import java.util.Optional;

@Repository
public interface StadiumVenueRepository<T extends StadiumVenue, ID> extends MongoRepository<T, ID> {
    @Query(value="{ '_class' : ?0, '_id': ?1 }")
    Optional<T> findByClassAndId(String className, Integer id);
}
