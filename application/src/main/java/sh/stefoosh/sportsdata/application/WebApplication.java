package sh.stefoosh.sportsdata.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.repository.MlbStadiumRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static sh.stefoosh.sportsdata.constants.Endpoints.MLB_STADIUM_RESOURCE;

@EnableMongoRepositories(basePackageClasses = MlbStadiumRepository.class)
@SpringBootApplication(scanBasePackages = {
		"sh.stefoosh.sportsdata.constants",
		"sh.stefoosh.sportsdata.repository",
		"sh.stefoosh.sportsdata.model",
})
@RestController
public class WebApplication {

	private static final Logger LOG = LoggerFactory.getLogger(WebApplication.class);

	@Autowired
	public MlbStadiumRepository mlbStadiumRepository;

	public WebApplication(MlbStadiumRepository mlbStadiumRepository) {
		this.mlbStadiumRepository = mlbStadiumRepository;
	}

	@GetMapping("/")
	public String root() {
		return "Hella Werld";
	}

	@GetMapping(MLB_STADIUM_RESOURCE)
	public List<MlbStadium> mlbStadiums(@RequestParam(required = false) Optional<Integer> id) {
		// TODO: log this in a filter along with request parameters and response
		LOG.debug(MLB_STADIUM_RESOURCE);

		return id.map(integer -> mlbStadiumRepository.findById(integer)
				.map(List::of)
				.orElse(Collections.emptyList()))
				.orElseGet(() -> mlbStadiumRepository.findAll());
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
