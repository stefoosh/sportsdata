package sh.stefoosh.sportsdata.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;
import sh.stefoosh.sportsdata.service.MongoDbService;
import sh.stefoosh.sportsdata.service.SportsDataService;
import sh.stefoosh.sportsdata.model.StadiumVenue;


import java.util.List;
import java.util.Arrays;

@EnableMongoRepositories(basePackageClasses = StadiumVenueRepository.class)
@SpringBootApplication(scanBasePackages = {"sh.stefoosh.sportsdata"})
public class Importer {

	private static final Logger LOG = LoggerFactory.getLogger(Importer.class);

	private final MongoDbService mongoDbService;

	private final SportsDataService sportsDataService;

	public Importer(MongoDbService mongoDbService, SportsDataService sportsDataService) {
		this.mongoDbService = mongoDbService;
		this.sportsDataService = sportsDataService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Importer.class, args);
	}

	private void beanDebugLogger(ApplicationContext ctx) {
		LOG.debug("EAT YOUR BEANS");
		Arrays.stream(ctx.getBeanDefinitionNames())
				.sorted()
				.forEach(LOG::debug);
		LOG.debug("FINISHED BEANS");
	}

	private void sportsDataProvingGround() {
		List<StadiumVenue> stadiumVenues = sportsDataService.getStadiumVenues();
		assert stadiumVenues != null;

		ObjectMapper mapper = new ObjectMapper();
		List<String> list = stadiumVenues.stream()
				.map(object -> mapper.convertValue(object, StadiumVenue.class))
				.map(StadiumVenue::toString)
				.toList();
		list.forEach(LOG::info);

		mongoDbService.stadiumVenueRepository.saveAll(stadiumVenues);
	}

	private enum Arguments {
		HELP,
		IMPORT
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			LOG.debug("Argument args.length=" + args.length);
			LOG.debug("Arguments args=" + Arrays.toString(args));

			beanDebugLogger(ctx);
			sportsDataProvingGround();
		};
	}
}
