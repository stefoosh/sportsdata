package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.Game;

import java.time.Instant;
import java.util.List;

@Repository
public interface GamesRepository<T extends Game, ID> extends MongoRepository<T, ID> {
    @Query(value = "{ '_class' : ?0, 'stadiumId': ?1 }")
    List<T> findByClassNameAndStadiumId(@NonNull String className, @NonNull int id);

    @Query(value = "{'sport': ?0, 'day': { $gte: ?1, $lte: ?2 }}")
    List<T> findBySportBetweenDates(@NonNull String sport, @NonNull Instant start, @NonNull Instant end);
}
