package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.Game;

@Repository
public interface GamesRepository<T extends Game, ID> extends MongoRepository<T, ID> {

}
