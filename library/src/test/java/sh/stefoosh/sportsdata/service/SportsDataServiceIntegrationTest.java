package sh.stefoosh.sportsdata.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.TestPropertySource;
import sh.stefoosh.sportsdata.constants.Sport;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.repository.StadiumVenueRepository;

import java.io.IOException;
import java.util.List;

import static sh.stefoosh.sportsdata.constants.Endpoints.MLB_STADIUM_RESOURCE;

@SpringBootTest
@TestPropertySource(locations = "/application.properties")
public class SportsDataServiceIntegrationTest {

    @EnableMongoRepositories(basePackageClasses = StadiumVenueRepository.class)
    @SpringBootApplication
    static class TestConfiguration {
    }

    public static MockWebServer mockBackEnd;

    @Autowired
    private SportsDataService sportsDataService;
    private ObjectMapper MAPPER = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @BeforeEach
    void initialize() {
        sportsDataService.setWebClientBaseUrl(String.format("http://localhost:%s", mockBackEnd.getPort()));
    }

    @Test
    void givenStadiumVenueResource_thenStream () throws JsonProcessingException, InterruptedException {
        StadiumVenue mockStadiumVenue = new StadiumVenue(
                Sport.mlb.name(),
                "some-mongodb-id",
                22,
                "SF",
                "Candlestick Park",
                "NorCal",
                "YouEssAay",
                6.9,
                9.6,
                10000
        );
        mockBackEnd.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(List.of(mockStadiumVenue)))
                .addHeader("Content-Type", "application/json"));

        List<StadiumVenue> stadiumVenues = sportsDataService.getMlbStadiums();

        Assertions.assertNotNull(stadiumVenues);
        Assertions.assertEquals(stadiumVenues.iterator().next(), mockStadiumVenue);
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals(MLB_STADIUM_RESOURCE, recordedRequest.getPath());
    }
}
