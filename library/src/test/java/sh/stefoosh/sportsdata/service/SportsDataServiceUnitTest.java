package sh.stefoosh.sportsdata.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sh.stefoosh.sportsdata.service.MlbStadiumResource;
import sh.stefoosh.sportsdata.service.SportsDataService;
import sh.stefoosh.sportsdata.service.StadiumVenue;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SportsDataServiceUnitTest {

    SportsDataService sportsDataService;
    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.ResponseSpec responseMock;

//    @BeforeEach
//    void setUp() {
//        sportsDataService = new SportsDataService(webClientMock);
//    }
//
//    @Disabled("Need to properly mock elementTypeRed")
//    @Test
//    void givenMlbStadiumsResource_thenReturnMlbStadiums() {
//        StadiumVenue mockStadiumVenue = new StadiumVenue(
//                "22",
//                "SF",
//                "Candlestick Park",
//                "NorCal",
//                "YouEssAay",
//                6.9,
//                9.6
//        );
//        StadiumVenue[] mockStadiumVenueArray = new StadiumVenue[]{mockStadiumVenue};
//        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
//        MlbStadiumResource mlbStadiumResource = new MlbStadiumResource();
//        when(requestHeadersUriMock.uri(mlbStadiumResource.getEndpoint())).thenReturn(requestHeadersMock);
//        when(requestHeadersMock.header(SUBSCRIPTION_KEY_HEADER, mlbStadiumResource.getApiKey())).thenReturn(requestHeadersMock);
//        when(requestHeadersMock.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersMock);
//        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
////        ParameterizedTypeReference<StadiumVenueResponseBody> elementTypeRef = ParameterizedTypeReference.forType(StadiumVenueResponseBody.class);
////        when(responseMock.bodyToMono(elementTypeRef)).thenReturn(Mono.just(mockStadiumVenueResponseBody));
//
//        ParameterizedTypeReference<StadiumVenue[]> elementTypeRef =
//                ParameterizedTypeReference.forType(StadiumVenue[].class);
//        when(responseMock.bodyToMono(elementTypeRef).thenReturn(Mono.just(mockStadiumVenueArray)));
//
//        List<StadiumVenue> stadiumVenues = sportsDataService.getStadiumVenues(mlbStadiumResource);
//
//        Assertions.assertNotNull(stadiumVenues);
//        Assertions.assertEquals(mockStadiumVenue, Arrays.asList(mockStadiumVenueArray));
//    }
}
