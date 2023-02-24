package sh.stefoosh.sportsdata.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import sh.stefoosh.sportsdata.model.MlbStadium;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

import java.util.Optional;
import java.util.stream.Stream;

import static sh.stefoosh.sportsdata.constants.Endpoint.*;
import static sh.stefoosh.sportsdata.constants.Package.*;

@EnableMongoRepositories(basePackages = SH_STEFOOSH_SPORTSDATA_REPOSITORY)
@SpringBootApplication(scanBasePackages = {
		SH_STEFOOSH_SPORTSDATA_CONSTANTS,
		SH_STEFOOSH_SPORTSDATA_REPOSITORY,
		SH_STEFOOSH_SPORTSDATA_MODEL,
})
@RestController
public class WebApplication {

	private static final Logger LOG = LoggerFactory.getLogger(WebApplication.class);

	private StadiumVenueRepository stadiumVenueRepository;

	public WebApplication(StadiumVenueRepository stadiumVenueRepository) {
		this.stadiumVenueRepository = stadiumVenueRepository;
	}

	@GetMapping("/")
	public String root() {
		// TODO: log request parameters and response
		return "Hella Werld";
	}

	@GetMapping(MLB_SCORES_JSON_STADIUMS)
	public Stream mlbStadium(@RequestParam(required = false) Optional<Integer> id) {

		LOG.debug(String.valueOf(id));
		return findStadiumVenue(MlbStadium.class, id);
	}

	@GetMapping(NHL_SCORES_JSON_STADIUMS)
	public Stream nhlArena(@RequestParam(required = false) Optional<Integer> id) {
		return findStadiumVenue(NhlArena.class, id);
	}

	@GetMapping(SOCCER_SCORES_JSON_VENUES)
	public Stream soccerVenue(@RequestParam(required = false) Optional<Integer> id) {
		return findStadiumVenue(SoccerVenue.class, id);
	}

	private <T extends StadiumVenue> Stream findStadiumVenue(Class<T> cls, Optional<Integer> id) {
		return id.isPresent() ?
				stadiumVenueRepository.findByClassNameAndId(cls.getName(), id.get()) :
				stadiumVenueRepository.findByClassName(cls.getName());
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
