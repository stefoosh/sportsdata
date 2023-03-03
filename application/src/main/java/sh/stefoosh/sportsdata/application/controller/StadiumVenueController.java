package sh.stefoosh.sportsdata.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sh.stefoosh.sportsdata.constants.Sport;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

import java.util.List;
import java.util.Optional;

@RestController
public final class StadiumVenueController {

    private static final Logger LOG = LoggerFactory.getLogger(StadiumVenueController.class);
    @Autowired
    private StadiumVenueRepository stadiumVenueRepository;

    private StadiumVenueController() {
    }

    @GetMapping("/{sportName}/location")
    public List<? extends StadiumVenue> getAllStadiumVenues(final @PathVariable String sportName) {
        Sport sport = Sport.valueOf(sportName);

        if (sport.equals(Sport.mlb)) {
            return findStadiumVenues(MlbStadium.class);
        }
        if (sport.equals(Sport.nhl)) {
            return findStadiumVenues(NhlArena.class);
        }
        if (sport.equals(Sport.soccer)) {
            return findStadiumVenues(SoccerVenue.class);
        }
        throw new IllegalArgumentException(
                String.format("Missing conditional returning StadiumVenue subclass list for enum %s", sport.name()));
    }

    @GetMapping(path = "/mlb/location/{id}", produces = "application/json")
    public ResponseEntity mlbStadium(final @PathVariable int id) {
        Optional doc = stadiumVenueRepository.findOneByClassNameAndStadiumId(MlbStadium.class.getName(), id);

        if (doc.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Some known 404 body");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.of(Optional.of(doc.get()));
    }

    @GetMapping(path = "/nhl/location/{id}", produces = "application/json")
    public List<NhlArena> nhlArena(final @PathVariable int id) {
        return findStadiumVenue(NhlArena.class, Optional.of(id));
    }

    @GetMapping(path = "/soccer/location/{id}", produces = "application/json")
    public List<SoccerVenue> soccerVenue(final @PathVariable int id) {
        return findStadiumVenue(SoccerVenue.class, Optional.of(id));
    }

    private <T extends StadiumVenue> List<T> findStadiumVenues(final Class<T> cls) {
        return findStadiumVenue(cls, Optional.empty());
    }

    private <T extends StadiumVenue> List<T> findStadiumVenue(final Class<T> cls, final Optional<Integer> id) {
        List<T> documents = id.isEmpty() || id.isPresent() && id.get() == 0
                ? stadiumVenueRepository.findByClassName(cls.getName())
                : "SoccerVenue".equals(cls.getSimpleName())
                ? stadiumVenueRepository.findByClassNameAndVenueId(cls.getName(), id.get())
                : stadiumVenueRepository.findByClassNameAndStadiumId(cls.getName(), id.get());

        String message = String.format("MongoDb query returned %s %s docs", cls.getSimpleName(), documents.size());
        if (documents.isEmpty()) {
            LOG.warn(message);
        } else {
            LOG.debug(message);
        }

        return documents;
    }
}
