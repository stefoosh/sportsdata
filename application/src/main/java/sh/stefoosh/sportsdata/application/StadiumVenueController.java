package sh.stefoosh.sportsdata.application;

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
		return id.isPresent() ?
				stadiumVenueRepository.findByClassNameAndId(cls.getName(), id.get()) :
				stadiumVenueRepository.findByClassName(cls.getName());
	}
}
