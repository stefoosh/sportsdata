package sh.stefoosh.sportsdata.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sh.stefoosh.sportsdata.constants.Sport;
import sh.stefoosh.sportsdata.model.Game;
import sh.stefoosh.sportsdata.model.MlbGame;
import sh.stefoosh.sportsdata.model.NhlGame;
import sh.stefoosh.sportsdata.repository.GamesRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static sh.stefoosh.sportsdata.constants.Endpoint.APPLICATION_JSON;

@RestController
@RequestMapping("/{sportName}/games")
public final class GamesController {
    private static final Logger LOG = LoggerFactory.getLogger(GamesController.class);

    @Autowired
    private GamesRepository gamesRepository;

    private GamesController() {
    }

    private Instant ymdToInstant(final String yearMonthDay) {
        LocalDate localDate = LocalDate.parse(yearMonthDay);
        LocalDateTime localDateTime = localDate.atStartOfDay();
        return localDateTime.toInstant(ZoneOffset.UTC);
    }

    @GetMapping(path = "/range", produces = APPLICATION_JSON)
    public List<? extends Game> getGamesBetweenDates(final @PathVariable String sportName,
                                                     final @RequestParam String start,
                                                     final @RequestParam String end) {

        return gamesRepository.findBySportBetweenDates(sportName, ymdToInstant(start), ymdToInstant(end));
    }

    @GetMapping(path = "/{id}", produces = APPLICATION_JSON)
    public List<? extends Game> getGame(final @PathVariable String sportName, final @PathVariable int id) {
        Sport sport = Sport.valueOf(sportName);
        if (sport.equals(Sport.nhl)) {
            return gamesRepository.findByClassNameAndStadiumId(NhlGame.class.getName(), id);
        }
        if (sport.equals(Sport.mlb)) {
            return gamesRepository.findByClassNameAndStadiumId(MlbGame.class.getName(), id);
        }
        return Collections.emptyList();
    }
}
