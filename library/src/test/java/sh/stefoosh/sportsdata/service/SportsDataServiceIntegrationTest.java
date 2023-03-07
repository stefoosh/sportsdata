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
import sh.stefoosh.sportsdata.model.MlbStadium;

import java.io.IOException;
import java.util.List;

import static sh.stefoosh.sportsdata.constants.Endpoint.MLB_SCORES_JSON_STADIUMS;

@SpringBootTest
public class SportsDataServiceIntegrationTest {

    private static MockWebServer mockUpstream;
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

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
    void givenMlbStadiumVenueResource_thenReturnList() throws JsonProcessingException, InterruptedException {
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

//    @Test
//    void givenMlbGamesResource_thenReturnList() throws JsonProcessingException, InterruptedException, ParseException {
//        MlbGame mockMlbGame = new MlbGame(
//                Sport.mlb,
//                "Scheduled",
//                simpleDateFormat.parse("2023-04-11T00:00:00"),
//                simpleDateFormat.parse("2023-04-11T19:00:00"),
//                simpleDateFormat.parse("2022-09-16T04:02:52"),
//                "THE",
//                "SOX",
//                3,
//                9,
//                22
//        );
//        mockUpstream.enqueue(new MockResponse().setBody(MAPPER.writeValueAsString(List.of(mockMlbGame)))
//                .addHeader("Content-Type", "application/json"));
//
//        List<MlbGame> mlbGames = sportsDataService.getMlbGames();
//
//        Assertions.assertNotNull(mlbGames);
//        Assertions.assertEquals(mlbGames.iterator().next(), mockMlbGame);
//        RecordedRequest recordedRequest = mockUpstream.takeRequest();
//        Assertions.assertEquals("GET", recordedRequest.getMethod());
//        Assertions.assertEquals(MLB_SCORES_JSON_GAMES, recordedRequest.getPath());
////        recordedRequest.getBody()
//    }

//    @Test
//    void givenNhlGamesResource_thenReturnTwoGamesInList() throws JsonProcessingException, InterruptedException, ParseException {
//        NhlGame mockNhlGameA = new NhlGame(
//                Sport.nhl,
//                "Scheduled",
//                simpleDateFormat.parse("2023-04-11T00:00:00"),
//                simpleDateFormat.parse("2023-04-11T19:00:00"),
//                simpleDateFormat.parse("2022-09-16T04:02:52"),
//                "FOO",
//                "BAR",
//                3,
//                9,
//                99
//        );
//        NhlGame mockNhlGameB = new NhlGame(
//                Sport.nhl,
//                "Scheduled",
//                simpleDateFormat.parse("2023-05-11T00:00:00"),
//                simpleDateFormat.parse("2023-05-11T19:00:00"),
//                simpleDateFormat.parse("2022-09-16T04:02:52"),
//                "FUZ",
//                "BAZ",
//                1,
//                2,
//                88
//        );
//        mockUpstream.enqueue(new MockResponse().setBody(
//                        MAPPER.writeValueAsString(List.of(mockNhlGameA, mockNhlGameB)))
//                .addHeader("Content-Type", "application/json"));
//
//        List<NhlGame> mlbGames = sportsDataService.getNhlGames();
//
//        Assertions.assertNotNull(mlbGames);
//        Assertions.assertEquals(2, mlbGames.size());
//        RecordedRequest recordedRequest = mockUpstream.takeRequest();
//        Assertions.assertEquals("GET", recordedRequest.getMethod());
//        Assertions.assertEquals(NHL_SCORES_JSON_GAMES, recordedRequest.getPath());
//    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
