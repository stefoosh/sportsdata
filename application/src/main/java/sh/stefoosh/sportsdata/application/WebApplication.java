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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static sh.stefoosh.sportsdata.constants.Endpoints.MLB_STADIUM_RESOURCE;
import static sh.stefoosh.sportsdata.constants.Endpoints.NHL_STADIUM_RESOURCE;
import static sh.stefoosh.sportsdata.constants.Endpoints.SOCCER_STADIUM_RESOURCE;

@EnableMongoRepositories(basePackages = "sh.stefoosh.sportsdata.repository")
@SpringBootApplication(scanBasePackages = {
		"sh.stefoosh.sportsdata.constants",
		"sh.stefoosh.sportsdata.repository",
		"sh.stefoosh.sportsdata.model",
})
@RestController
public class WebApplication {

	private static final Logger LOG = LoggerFactory.getLogger(WebApplication.class);

	@Autowired
	public StadiumVenueRepository stadiumVenueRepository;

	public WebApplication(StadiumVenueRepository stadiumVenueRepository) {
		this.stadiumVenueRepository = stadiumVenueRepository;
	}

	@GetMapping("/")
	public String root() {
		// TODO: log request parameters and response
		return "Hella Werld";
	}

	@GetMapping(MLB_STADIUM_RESOURCE)
	public List<MlbStadium> mlbStadium(@RequestParam Integer id) {
		return findStadiumVenue(MlbStadium.class, id);
	}

	@GetMapping(NHL_STADIUM_RESOURCE)
	public List<NhlArena> nhlArena(@RequestParam Integer id) {
		return findStadiumVenue(NhlArena.class, id);
	}

	@GetMapping(SOCCER_STADIUM_RESOURCE)
	public List<SoccerVenue> soccerVenue(@RequestParam Integer id) {
		return findStadiumVenue(SoccerVenue.class, id);
	}

	private <T extends StadiumVenue> List<T> findStadiumVenue(Class<T> cls, Integer id) {
		Optional<T> stadiumVenue = stadiumVenueRepository.findByClassAndId(cls.getName(), id);
		return stadiumVenue.map(List::of).orElse(Collections.emptyList());

//		return id.map(integer -> mlbStadiumRepository.findByIdAndClass(integer, MlbStadium.class.getName())
//				.map(List::of)
//				.orElse(Collections.emptyList()))
//				.orElseGet(() -> mlbStadiumRepository.findAll());
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
