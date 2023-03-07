package sh.stefoosh.sportsdata.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import sh.stefoosh.sportsdata.constants.Sport;
import sh.stefoosh.sportsdata.model.Game;
import sh.stefoosh.sportsdata.model.MlbGame;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.model.NhlGame;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.GamesRepository;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;
import sh.stefoosh.sportsdata.service.SportsDataService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA;
import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_REPOSITORY;

@EnableMongoRepositories(basePackages = SH_STEFOOSH_SPORTSDATA_REPOSITORY)
@SpringBootApplication(scanBasePackages = {SH_STEFOOSH_SPORTSDATA})
public class Importer implements CommandLineRunner, ExitCodeGenerator {
    private static final Logger LOG = LoggerFactory.getLogger(Importer.class);

    private int exitCode = 1;

    @Autowired
    private StadiumVenueRepository stadiumVenueRepository;

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private SportsDataService sportsDataService;

    public static void main(final String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(Importer.class, args)));
    }

    private <T, R extends MongoRepository> void saveAllModels(final List<T> upstreamResponse, final R repository) {
        LOG.debug("{} objects fetched", upstreamResponse.size());
        LOG.debug("{}", upstreamResponse);

        List<T> saveAllResult = repository.saveAll(upstreamResponse);
        LOG.debug("{} documents saved", saveAllResult.size());
        LOG.debug("{}", saveAllResult);

        if (upstreamResponse.size() != saveAllResult.size()) {
            LOG.error("Number of objects fetched {} and saved {} should match",
                    upstreamResponse.size(), saveAllResult.size());
        }
    }

    private List<? extends Game> embedDocument(final List<? extends StadiumVenue> locations,
                                               final List<? extends Game> games) {
        return games.stream().peek(
                        game -> {
                            Optional<? extends StadiumVenue> matchingLocation = locations.stream()
                                    .filter(location -> location.getStadiumId() == game.getStadiumId())
                                    .findFirst();
                            if (matchingLocation.isPresent()) {
                                game.setLocation(matchingLocation.get());
                            } else {
                                LOG.error("No match for game.getStadiumId={}", game.getStadiumId());
                            }
                        })
                .collect(Collectors.toList());
    }

    @Override
    public final void run(final String... args) {
        String requiredPositionalArgumentChoices = String.format("Two positional arguments required: %s %s",
                Arrays.toString(Sport.values()), Arrays.toString(DataCategory.values()));

        if (args.length == 2) {
            try {
                Sport sportArgument = Sport.valueOf(args[0]);
                DataCategory categoryArgument = DataCategory.valueOf(args[1]);

                // delete before writing to repo
                // catch upstream communication exception
                if (sportArgument.equals(Sport.nhl)) {
                    if (categoryArgument.equals(DataCategory.games)) {
                        List<NhlArena> upstreamArenas = sportsDataService.getNhlStadiums();
                        List<NhlGame> upstreamGames = sportsDataService.getNhlGames();
                        List<NhlGame> embeddedDocs = (List<NhlGame>) embedDocument(upstreamArenas, upstreamGames);

                        embeddedDocs.forEach(doc -> LOG.debug(doc.toString()));

                        saveAllModels(embeddedDocs, gamesRepository);
                    }
                }

                if (sportArgument.equals(Sport.mlb)) {
                    if (categoryArgument.equals(DataCategory.games)) {
                        List<MlbStadium> upstreamStadiums = sportsDataService.getMlbStadiums();
                        List<MlbGame> upstreamGames = sportsDataService.getMlbGames();
                        List<MlbGame> embeddedDocs = (List<MlbGame>) embedDocument(upstreamStadiums, upstreamGames);

                        embeddedDocs.forEach(doc -> LOG.debug(doc.toString()));

                        saveAllModels(embeddedDocs, gamesRepository);
                    }
                }

                exitCode = 0;
            } catch (IllegalArgumentException e) {
                LOG.error(requiredPositionalArgumentChoices);
                LOG.error("Incorrect arguments sent=" + Arrays.toString(args));
            } catch (Exception e) {
                LOG.error(e.toString());
            }
        } else {
            LOG.error(requiredPositionalArgumentChoices);
            if (args.length > 0) {
                LOG.error("Args=" + Arrays.toString(args));
            }
        }
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    private enum DataCategory {
        locations,
        games
    }
}
