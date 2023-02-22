package sh.stefoosh.sportsdata.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
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
	private StadiumVenueRepository stadiumVenueRepository;

	@Autowired
	private SportsDataService sportsDataService;

	public static void main(String[] args) {
		SpringApplication.run(Importer.class, args);
	}

	private void dispatchStadiumVenues(List<StadiumVenue> upstream) {
		LOG.debug("{} objects fetched", upstream.size());
		LOG.debug("{}", upstream);

		List<StadiumVenue> saveAllResult = stadiumVenueRepository.saveAll(upstream);
		LOG.debug("{} documents saved", saveAllResult.size());
		LOG.debug("{}", saveAllResult);

		if (upstream.size() != saveAllResult.size()) {
			LOG.error("Number of objects fetched {} and saved {} should match",
					upstream.size(), saveAllResult.size());
		}
	}

	private void sportsDataProvingGround() {
		List<StadiumVenue> upstreamMlbStadiums = sportsDataService.getMlbStadiums();
//		List<StadiumVenue> upstreamNhlStadiums = sportsDataService.getNhlStadiums();
//		List<StadiumVenue> upstreamSoccerStadiums = sportsDataService.getSoccerStadiums();

		dispatchStadiumVenues(upstreamMlbStadiums);
//		dispatchStadiumVenues(upstreamNhlStadiums, Sport.nhl);
//		dispatchStadiumVenues(upstreamSoccerStadiums, Sport.soccer);
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
