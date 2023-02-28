package sh.stefoosh.sportsdata.application.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

import java.util.List;

import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_REPOSITORY;
import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_WEB;

@ComponentScan({
        SH_STEFOOSH_SPORTSDATA_WEB,
        SH_STEFOOSH_SPORTSDATA_REPOSITORY
})
@Testcontainers
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class StadiumVenueControllerUnitTest {
    private static final Logger LOG = LoggerFactory.getLogger(StadiumVenueControllerUnitTest.class);

    //    @Container
    static MongoDBContainer mongoDBContainer = MongoContainers.getDefaultContainer();

    static {
        mongoDBContainer.start();
    }

    @Autowired
    private StadiumVenueRepository stadiumVenueRepository;
    @Autowired
    private StadiumVenueController stadiumVenueController;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @AfterEach
    void cleanUp() {
        this.stadiumVenueRepository.deleteAll();
    }

    @Test
    void givenOneMlbStadium_shouldReturnSameMlbStadium() {
        MlbStadium exptectedMlbStadium = new MlbStadium(
                22,
                "SF",
                "Candlestick Park",
                "NorCal",
                "YouEssAay",
                6.9,
                9.6,
                99999
        );
        this.stadiumVenueRepository.save(exptectedMlbStadium);

        List<MlbStadium> controllerResponse = stadiumVenueController.mlbStadium(22);

        Assertions.assertEquals(1, controllerResponse.size());
        Assertions.assertEquals(22, controllerResponse.get(0).getStadiumId());
        Assertions.assertEquals(exptectedMlbStadium, controllerResponse.get(0));
    }

    @Test
    void givenNoNhlArenas_shouldReturnAllNhlArenas() {
        NhlArena expectedArenaOne = new NhlArena(
                1,
                "Selland",
                "Fresno",
                "CenCal",
                "USandA",
                55.9,
                5.59,
                999
        );
        NhlArena expectedArenaTwo = new NhlArena(
                2,
                "SaveMart",
                "Clovis",
                "CenCal",
                "USandA",
                5.9,
                5.5,
                9999
        );
        this.stadiumVenueRepository.saveAll(List.of(expectedArenaOne, expectedArenaTwo));

        List<NhlArena> controllerResponse = stadiumVenueController.nhlArena(0);

        Assertions.assertEquals(2, controllerResponse.size());
    }

    @Test
    void givenSoccerIdNotInDb_shouldReturnEmptyList() {
        int idNotInDb = -1;

        List<SoccerVenue> controllerResponse = stadiumVenueController.soccerVenue(idNotInDb);

        Assertions.assertEquals(0, controllerResponse.size());
    }

    @Test
    void givenSoccerId_shouldReturnOneSoccerVenue() {
        int expectedId = 202;
        SoccerVenue expectedStadium = new SoccerVenue(
                expectedId,
                "Hotspur Stadium",
                "Tottenham",
                "London City",
                "England",
                1.0,
                0.1,
                99999
        );
        this.stadiumVenueRepository.save(expectedStadium);

        List<SoccerVenue> controllerResponse = stadiumVenueController.soccerVenue(expectedId);

        Assertions.assertEquals(1, controllerResponse.size());
        Assertions.assertEquals(expectedId, controllerResponse.get(0).getVenueId());
    }
}
