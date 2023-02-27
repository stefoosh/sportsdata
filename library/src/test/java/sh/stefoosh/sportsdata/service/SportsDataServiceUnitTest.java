package sh.stefoosh.sportsdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(MockitoExtension.class)

@SpringBootTest("service.apiAuthHeaderKey=Hella")
public class SportsDataServiceUnitTest {
    @Autowired
    private SportsDataService sportsDataService;

    @SpringBootApplication
    static class TestConfiguration {
    }

//    SportsDataService sportsDataService;
//    @Mock
//    private WebClient webClientMock;
//    @Mock
//    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
//    @Mock
//    private WebClient.RequestHeadersSpec requestHeadersMock;
//    @Mock
//    private WebClient.ResponseSpec responseMock;

//    @BeforeEach
//    void setUp() {
//        sportsDataService = new SportsDataService(webClientMock);
//    }
//
//    @Disabled("Need to properly mock elementTypeRed")
//    @Test
//    void givenMlbStadiumsResource_thenReturnMlbStadiums() {
//        MlbStadium mockStadiumVenue = new MlbStadium(
//                "22",
//                "SF",
//                "Candlestick Park",
//                "NorCal",
//                "YouEssAay",
//                6.9,
//                9.6
//        );
//        MlbStadium[] mockStadiumVenueArray = new MlbStadium[]{mockStadiumVenue};
//        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
//        MlbStadiumResource mlbStadiumResource = new MlbStadiumResource();
//        when(requestHeadersUriMock.uri(mlbStadiumResource.getEndpoint())).thenReturn(requestHeadersMock);
//        when(requestHeadersMock.header(SUBSCRIPTION_KEY_HEADER, mlbStadiumResource.getApiKey())).thenReturn(requestHeadersMock);
//        when(requestHeadersMock.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersMock);
//        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
////        ParameterizedTypeReference<StadiumVenueResponseBody> elementTypeRef = ParameterizedTypeReference.forType(StadiumVenueResponseBody.class);
////        when(responseMock.bodyToMono(elementTypeRef)).thenReturn(Mono.just(mockStadiumVenueResponseBody));
//
//        ParameterizedTypeReference<MlbStadium[]> elementTypeRef =
//                ParameterizedTypeReference.forType(MlbStadium[].class);
//        when(responseMock.bodyToMono(elementTypeRef).thenReturn(Mono.just(mockStadiumVenueArray)));
//
//        List<MlbStadium> stadiumVenues = sportsDataService.getStadiumVenues(mlbStadiumResource);
//
//        Assertions.assertNotNull(stadiumVenues);
//        Assertions.assertEquals(mockStadiumVenue, Arrays.asList(mockStadiumVenueArray));
//    }
}
