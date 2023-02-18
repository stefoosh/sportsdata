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

import java.io.IOException;
import java.util.List;

public class SportsDataServiceIntegrationTest {

    public static MockWebServer mockBackEnd;
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
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        sportsDataService = new SportsDataService(baseUrl);
    }

    @Test
    void givenMlbStadiumsResource_thenReturnMlbStadiums() throws JsonProcessingException, InterruptedException {
        StadiumVenue mockStadiumVenue = new StadiumVenue(
                "22",
                "SF",
                "Candlestick Park",
                "NorCal",
                "YouEssAay",
                6.9,
                9.6
        );
        MlbStadiumResource mlbStadiumResource = new MlbStadiumResource();
        mockBackEnd.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(List.of(mockStadiumVenue)))
                .addHeader("Content-Type", "application/json"));

        List<StadiumVenue> stadiumVenuesList = sportsDataService.getStadiumVenues(mlbStadiumResource);

        Assertions.assertNotNull(stadiumVenuesList);
        Assertions.assertEquals(stadiumVenuesList.get(0), mockStadiumVenue);
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals(mlbStadiumResource.getEndpoint(), recordedRequest.getPath());
    }
}
