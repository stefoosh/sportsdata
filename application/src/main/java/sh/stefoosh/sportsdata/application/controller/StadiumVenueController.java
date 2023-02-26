package sh.stefoosh.sportsdata.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
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
@RestController public class StadiumVenueController {

	private static final Logger LOG = LoggerFactory.getLogger(StadiumVenueController.class);

	@Autowired
	private StadiumVenueRepository stadiumVenueRepository;

	@GetMapping(MLB_SCORES_JSON_STADIUMS)
	public List<MlbStadium> mlbStadiums(@RequestParam(required = false) Optional<Integer> id) {
		return findStadiumVenue(MlbStadium.class, id);
	}

	@GetMapping(NHL_SCORES_JSON_STADIUMS)
	public List<NhlArena> nhlArenas(@RequestParam(required = false) Optional<Integer> id) {
		return findStadiumVenue(NhlArena.class, id);
	}

	@GetMapping(SOCCER_SCORES_JSON_VENUES)
	public List<SoccerVenue> soccerVenues(@RequestParam(required = false) Optional<Integer> id) {
		return findStadiumVenue(SoccerVenue.class, id);
	}

	private <T extends StadiumVenue> List<T> findStadiumVenue(Class<T> cls, Optional<Integer> id) {
		// TODO: use reflection to call a method that returns the idFieldName
		//  and pass it as the second arg in findByClassNameAndIdFieldName(String, String, int)
		List<T> documents = id.isEmpty() || id.isPresent() && id.get() == 0 ?
				stadiumVenueRepository.findByClassName(cls.getName()) :
				"SoccerVenue" == cls.getSimpleName() ?
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
