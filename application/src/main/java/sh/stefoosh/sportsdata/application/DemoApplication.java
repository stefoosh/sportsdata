package sh.stefoosh.sportsdata.application;

import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;
import sh.stefoosh.sportsdata.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMongoRepositories(basePackageClasses = StadiumVenueRepository.class)
@SpringBootApplication(scanBasePackages = "sh.stefoosh.sportsdata")
@RestController
public class DemoApplication {

	private final MyService myService;

	public DemoApplication(MyService myService) {
		this.myService = myService;
	}

	@GetMapping("/")
	public String home() {
		return myService.message();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
