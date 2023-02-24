package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.StadiumVenue;

import java.util.stream.Stream;

@Repository
public interface StadiumVenueRepository<T extends StadiumVenue, ID> extends MongoRepository<T, ID> {

    // this method is an example of a property expression that can't be interpreted
    // findByIdAndClass resolves Id but Class won't. So reversing the field order with a declared query is needed
    @Query(value="{ '_class' : ?0, '_id': ?1 }")
    Stream<T> findByClassNameAndId(String className, Integer id);

    @Query(value="{ '_class' : ?0 }")
    Stream<T> findByClassName(String className);
}
