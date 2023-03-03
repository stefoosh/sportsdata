package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.StadiumVenue;

import java.util.List;
import java.util.Optional;

@Repository
public interface StadiumVenueRepository<T extends StadiumVenue, ID> extends MongoRepository<T, ID> {

    // this method is an example of a property expression that can't be interpreted
    // findByIdAndClass resolves Id but Class won't. So reversing the field order with a declared query is needed
    @Query(value = "{ '_class' : ?0, 'stadiumId': ?1 }")
    List<T> findByClassNameAndStadiumId(@NonNull String className, @NonNull int id);

    @Aggregation(pipeline = {
            "{ '$match': {'_class' : ?0, 'stadiumId': ?1} }",
            "{ '$limit': 1 }"
    })
    Optional<T> findOneByClassNameAndStadiumId(@NonNull String className, @NonNull int id);

    @Query(value = "{ '_class' : ?0, 'venueId': ?1 }")
    List<T> findByClassNameAndVenueId(@NonNull String className, @NonNull int id);

    @Query(value = "{ '_class' : ?0 }")
    List<T> findByClassName(@NonNull String className);
}
