package sh.stefoosh.sportsdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

@Service
public class MongoDbService {

    @Autowired
    public StadiumVenueRepository stadiumVenueRepository;

    public MongoDbService() {

    }
}
