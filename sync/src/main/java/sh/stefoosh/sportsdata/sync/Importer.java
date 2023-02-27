package sh.stefoosh.sportsdata.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;
import sh.stefoosh.sportsdata.service.SportsDataService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA;
import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_REPOSITORY;

@EnableMongoRepositories(basePackages = SH_STEFOOSH_SPORTSDATA_REPOSITORY)
@SpringBootApplication(scanBasePackages = {SH_STEFOOSH_SPORTSDATA})
public class Importer implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(Importer.class);

    @Autowired
    private StadiumVenueRepository stadiumVenueRepository;

    @Autowired
    private SportsDataService sportsDataService;

    public static void main(final String[] args) {
        SpringApplication.run(Importer.class, args);
    }

    private <T extends StadiumVenue> void saveAllStadiumVenues(final List<T> upstream) {
        LOG.debug("{} objects fetched", upstream.size());
        LOG.debug("{}", upstream);

        List<T> saveAllResult = stadiumVenueRepository.saveAll(upstream);
        LOG.debug("{} documents saved", saveAllResult.size());
        LOG.debug("{}", saveAllResult);

        if (upstream.size() != saveAllResult.size()) {
            LOG.error("Number of objects fetched {} and saved {} should match",
                    upstream.size(), saveAllResult.size());
        }
    }

    private Stream<List<? extends StadiumVenue>> fetchUpstreamStadiumVenues() {
        List<MlbStadium> upstreamMlbStadiums = sportsDataService.getMlbStadiums();
        List<NhlArena> upstreamNhlStadiums = sportsDataService.getNhlStadiums();
        List<SoccerVenue> upstreamSoccerStadiums = sportsDataService.getSoccerStadiums();

        return Stream.of(upstreamMlbStadiums, upstreamNhlStadiums, upstreamSoccerStadiums);
    }

    @Override
    public final void run(final String... args) throws Exception {

        LOG.debug("Argument args.length=" + args.length);
        LOG.debug("Arguments args=" + Arrays.toString(args));

        fetchUpstreamStadiumVenues().forEach(this::saveAllStadiumVenues);

    }

    private enum Arguments {
        HELP,
        IMPORT
    }


}
