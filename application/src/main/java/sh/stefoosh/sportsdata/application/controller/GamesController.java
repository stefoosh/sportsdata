package sh.stefoosh.sportsdata.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.stefoosh.sportsdata.model.Game;
import sh.stefoosh.sportsdata.model.MlbGame;
import sh.stefoosh.sportsdata.model.NhlGame;
import sh.stefoosh.sportsdata.repository.GamesRepository;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/games")
public final class GamesController {
    private static final Logger LOG = LoggerFactory.getLogger(GamesController.class);

    @Autowired
    private GamesRepository gamesRepository;

    private GamesController() {
    }

    @GetMapping("/{sport}/location/{id}")
    public List<? extends Game> getGame(final @PathVariable String sport, final @PathVariable int id) {
        if ("nhl".equals(sport)) {
            return gamesRepository.findByClassNameAndStadiumId(NhlGame.class.getName(), id);
        }
        if ("mlb".equals(sport)) {
            return gamesRepository.findByClassNameAndStadiumId(MlbGame.class.getName(), id);
        }
        return Collections.emptyList();
    }
}
