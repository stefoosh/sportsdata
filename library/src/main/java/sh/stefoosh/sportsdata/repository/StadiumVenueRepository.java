package sh.stefoosh.sportsdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import sh.stefoosh.sportsdata.model.StadiumVenue;

@Repository
public interface StadiumVenueRepository extends MongoRepository<StadiumVenue, String> {

}
