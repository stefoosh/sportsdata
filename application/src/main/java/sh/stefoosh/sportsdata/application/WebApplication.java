package sh.stefoosh.sportsdata.application;

import org.springframework.beans.factory.annotation.Autowired;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableMongoRepositories(basePackageClasses = StadiumVenueRepository.class)
@SpringBootApplication(scanBasePackages = "sh.stefoosh.sportsdata")
@RestController
public class WebApplication {

	@Autowired
	public StadiumVenueRepository stadiumVenueRepository;

	public WebApplication() {
	}

	@GetMapping("/")
	public List<StadiumVenue> home() {
		return stadiumVenueRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
