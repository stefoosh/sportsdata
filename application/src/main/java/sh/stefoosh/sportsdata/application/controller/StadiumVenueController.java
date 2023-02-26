package sh.stefoosh.sportsdata.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sh.stefoosh.sportsdata.model.MlbStadium;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

import java.util.List;
import java.util.Optional;

import static sh.stefoosh.sportsdata.constants.Endpoint.*;
import static sh.stefoosh.sportsdata.constants.Package.*;

@EnableMongoRepositories(basePackages = SH_STEFOOSH_SPORTSDATA_REPOSITORY)
@RestController
@RequestMapping("/location")
public class StadiumVenueController {

	private static final Logger LOG = LoggerFactory.getLogger(StadiumVenueController.class);

	@Autowired
	private StadiumVenueRepository stadiumVenueRepository;

	private enum Sport {
		mlb,
		nhl,
		soccer
	}

	@GetMapping("/{sportName}")
	public List<? extends StadiumVenue> getAllStadiumVenues(@PathVariable String sportName) {
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

	@GetMapping(MLB_STADIUMS + "/{id}")
	public List<MlbStadium> mlbStadium(@PathVariable int id) {
		return findStadiumVenue(MlbStadium.class, Optional.of(id));
	}

	@GetMapping(NHL_ARENAS + "/{id}")
	public List<NhlArena> nhlArena(@PathVariable int id) {
		return findStadiumVenue(NhlArena.class, Optional.of(id));
	}

	@GetMapping(SOCCER_VENUES + "/{id}")
	public List<SoccerVenue> soccerVenue(@PathVariable int id) {
		return findStadiumVenue(SoccerVenue.class, Optional.of(id));
	}

	private <T extends StadiumVenue> List<T> findStadiumVenues(Class<T> cls) {
		return findStadiumVenue(cls, Optional.empty());
	}

	private <T extends StadiumVenue> List<T> findStadiumVenue(Class<T> cls, Optional<Integer> id) {
		// TODO: use reflection to call a method that returns the idFieldName
		//  and pass it as the second arg in findByClassNameAndIdFieldName(String, String, int)
		List<T> documents = id.isEmpty() || id.isPresent() && id.get() == 0 ?
				stadiumVenueRepository.findByClassName(cls.getName()) :
				"SoccerVenue".equals(cls.getSimpleName()) ?
						stadiumVenueRepository.findByClassNameAndVenueId(cls.getName(), id.get()) :
						stadiumVenueRepository.findByClassNameAndStadiumId(cls.getName(), id.get());

		String message = String.format("MongoDb query returned %s %s docs", cls.getSimpleName(), documents.size());
		if (documents.isEmpty()) {
			LOG.warn(message);
		} else {
			LOG.debug(message);
		}

		return documents;
	}
}
