package sh.stefoosh.sportsdata.application;

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
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

import java.util.List;
import java.util.Optional;

import static sh.stefoosh.sportsdata.constants.Package.SH_STEFOOSH_SPORTSDATA_WEB;

@ComponentScan({SH_STEFOOSH_SPORTSDATA_WEB})
@Testcontainers
@ExtendWith(SpringExtension.class)
@DataMongoTest
public class StadiumVenueControllerTest {
    private static final Logger LOG = LoggerFactory.getLogger(StadiumVenueControllerTest.class);

    @Container
    static MongoDBContainer mongoDBContainer = MongoContainers.getDefaultContainer();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private StadiumVenueRepository stadiumVenueRepository;

    @Autowired
    private StadiumVenueController stadiumVenueController;

    @AfterEach
    void cleanUp() {
        this.stadiumVenueRepository.deleteAll();
    }

    @Test
    void givenMlbStadium_repoShouldReturnSameMlbStadium() {
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

        List<MlbStadium> controllerResponse = stadiumVenueController.mlbStadiums(Optional.of(22));

        Assertions.assertEquals(1, controllerResponse.size());
        Assertions.assertEquals(22, controllerResponse.get(0).getId());
        Assertions.assertEquals(exptectedMlbStadium, controllerResponse.get(0));
    }
}
