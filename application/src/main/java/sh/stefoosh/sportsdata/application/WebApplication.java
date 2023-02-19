package sh.stefoosh.sportsdata.application;

import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;
import sh.stefoosh.sportsdata.service.MlbStadiumResource;
import sh.stefoosh.sportsdata.service.MongoDbService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.stefoosh.sportsdata.service.StadiumVenueResource;

import java.util.List;

@EnableMongoRepositories(basePackageClasses = StadiumVenueRepository.class)
@SpringBootApplication(scanBasePackages = "sh.stefoosh.sportsdata")
@RestController
public class WebApplication {

	private final MongoDbService mongoDbService;

	public WebApplication(MongoDbService mongoDbService) {
		this.mongoDbService = mongoDbService;
	}

	@GetMapping(MlbStadiumResource.END_POINT)
	public List<StadiumVenue> home() {
		return mongoDbService.stadiumVenueRepository.findAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
