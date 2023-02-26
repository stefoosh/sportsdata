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
import org.springframework.test.context.TestPropertySource;
import sh.stefoosh.sportsdata.model.MlbStadium;

import java.io.IOException;
import java.util.List;

import static sh.stefoosh.sportsdata.constants.Endpoint.MLB_SCORES_JSON_STADIUMS;

@SpringBootTest
//@TestPropertySource(locations = "/application.properties")
public class SportsDataServiceIntegrationTest {

    @SpringBootApplication
    static class TestConfiguration {
    }

    public static MockWebServer mockUpstream;

    @Autowired
    private SportsDataService sportsDataService;
    private ObjectMapper MAPPER = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        mockUpstream = new MockWebServer();
        mockUpstream.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockUpstream.shutdown();
    }

    @BeforeEach
    void initialize() {
        sportsDataService.setWebClientBaseUrl(String.format("http://localhost:%s", mockUpstream.getPort()));
    }

    @Test
    void givenMlbStadiumVenueResource_thenReturnList () throws JsonProcessingException, InterruptedException {
        MlbStadium mockMlbStadium = new MlbStadium(
                22,
                "SF",
                "Candlestick Park",
                "NorCal",
                "YouEssAay",
                6.9,
                9.6,
                10000
        );
        mockUpstream.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(List.of(mockMlbStadium)))
                .addHeader("Content-Type", "application/json"));

        List<MlbStadium> mlbStadiums = sportsDataService.getMlbStadiums();

        Assertions.assertNotNull(mlbStadiums);
        Assertions.assertEquals(mlbStadiums.iterator().next(), mockMlbStadium);
        RecordedRequest recordedRequest = mockUpstream.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals(MLB_SCORES_JSON_STADIUMS, recordedRequest.getPath());
    }
}
