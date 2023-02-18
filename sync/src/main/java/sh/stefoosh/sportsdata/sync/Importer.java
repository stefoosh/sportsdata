package sh.stefoosh.sportsdata.sync;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import sh.stefoosh.sportsdata.service.MlbStadiumResource;
import sh.stefoosh.sportsdata.service.SportsDataService;
import sh.stefoosh.sportsdata.service.StadiumVenue;

import java.util.List;
import java.util.Arrays;

import static sh.stefoosh.sportsdata.service.Props.SPORTS_DATA_API_V_3_URI;

@SpringBootApplication(scanBasePackages = "sh.stefoosh.sportsdata")
public class Importer {

	private static final Logger LOG = LoggerFactory.getLogger(Importer.class);

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
		WebClient webClient = WebClient.builder().baseUrl(SPORTS_DATA_API_V_3_URI).build();
		SportsDataService sportsDataService = new SportsDataService(webClient);
		List<StadiumVenue> stadiumVenues = sportsDataService.getStadiumVenues(new MlbStadiumResource());
		assert stadiumVenues != null;

		ObjectMapper mapper = new ObjectMapper();
		List<String> list = stadiumVenues.stream()
				.map(object -> mapper.convertValue(object, StadiumVenue.class))
				.map(StadiumVenue::toString)
				.toList();

		list.forEach(LOG::info);
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
