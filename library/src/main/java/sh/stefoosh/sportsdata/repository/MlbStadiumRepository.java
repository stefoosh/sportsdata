package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.MlbStadium;

@Repository
public interface MlbStadiumRepository extends MongoRepository<MlbStadium, Integer> {

}
