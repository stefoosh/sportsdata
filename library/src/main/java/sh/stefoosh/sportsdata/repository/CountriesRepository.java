package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sh.stefoosh.sportsdata.model.Countries;

public interface CountriesRepository extends MongoRepository<Countries, String> {
}
