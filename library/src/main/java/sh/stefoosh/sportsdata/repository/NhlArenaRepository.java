package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.NhlArena;

@Repository
public interface NhlArenaRepository extends MongoRepository<NhlArena, Integer> {

}
