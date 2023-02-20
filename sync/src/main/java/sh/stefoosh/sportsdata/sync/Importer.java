package sh.stefoosh.sportsdata.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sh.stefoosh.sportsdata.constants.Sport;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;
import sh.stefoosh.sportsdata.service.SportsDataService;
import sh.stefoosh.sportsdata.model.StadiumVenue;


import java.util.List;
import java.util.Arrays;

@EnableMongoRepositories(basePackageClasses = StadiumVenueRepository.class)
@SpringBootApplication(scanBasePackages = {"sh.stefoosh.sportsdata"})
public class Importer {

	private static final Logger LOG = LoggerFactory.getLogger(Importer.class);

	@Autowired
	public StadiumVenueRepository stadiumVenueRepository;

	private final SportsDataService sportsDataService;

	public Importer(SportsDataService sportsDataService) {
		this.sportsDataService = sportsDataService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Importer.class, args);
	}

	private List<StadiumVenue> embedStadiumVenue(List<StadiumVenue> stadiumVenues, Sport sport) {
		stadiumVenues.forEach(stadiumVenue -> stadiumVenue.setSport(sport.name()));
		return stadiumVenues;
	}

	private void sportsDataProvingGround() {
		List<StadiumVenue> upstreamMlbStadiums = sportsDataService.getMlbStadiums();
		LOG.debug("Fetched {} {}", upstreamMlbStadiums.size(), upstreamMlbStadiums);
		List<StadiumVenue> documentMlbStadiums = embedStadiumVenue(upstreamMlbStadiums, Sport.mlb);
		stadiumVenueRepository.saveAll(documentMlbStadiums);

		List<StadiumVenue> upstreamNhlStadiums = sportsDataService.getNhlStadiums();
		LOG.debug("Fetched {} {}", upstreamNhlStadiums.size(), upstreamNhlStadiums);
		List<StadiumVenue> documentNhlStadiums = embedStadiumVenue(upstreamNhlStadiums, Sport.nhl);
		stadiumVenueRepository.saveAll(documentNhlStadiums);
	}

	private enum Arguments {
		HELP,
		IMPORT
	}

	private void beanDebugLogger(ApplicationContext ctx) {
		LOG.debug("EAT YOUR BEANS");
		Arrays.stream(ctx.getBeanDefinitionNames())
				.sorted()
				.forEach(LOG::debug);
		LOG.debug("FINISHED BEANS");
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
