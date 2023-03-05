package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sh.stefoosh.sportsdata.model.States;

import java.util.List;

public interface StatesRepository extends MongoRepository<States, String> {
    List<States> findByCountryNameOrderByName(String countryName);
}
