package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sh.stefoosh.sportsdata.model.Countries;

import java.util.List;

public interface CountriesRepository extends MongoRepository<Countries, String> {
    List<Countries> findAllByOrderByName();
}
