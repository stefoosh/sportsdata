package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sh.stefoosh.sportsdata.model.States;

public interface StatesRepository extends MongoRepository<States, String> {
}
