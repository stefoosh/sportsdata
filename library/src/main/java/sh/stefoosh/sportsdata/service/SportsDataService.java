package sh.stefoosh.sportsdata.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

import static sh.stefoosh.sportsdata.service.Props.SPORTS_DATA_API_V_3_URI;
import static sh.stefoosh.sportsdata.service.Props.SUBSCRIPTION_KEY_HEADER;

public class SportsDataService {

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private WebClient webClient;

    public SportsDataService() {
        setWebClient(WebClient.builder().baseUrl(SPORTS_DATA_API_V_3_URI).build());
    }

    public void setWebClientBaseUrl(String baseUrl) {
        setWebClient(WebClient.builder().baseUrl(baseUrl).build());
    }

    public List<StadiumVenue> getStadiumVenues(StadiumVenueResource resource) {
        return Arrays.asList(getResponseBody(resource, StadiumVenue[].class));
    }

    private <T extends Resource, G> G getResponseBody(T resource, Class<G> cls) {
        ParameterizedTypeReference<G> typeRef = ParameterizedTypeReference.forType(cls);

        return getWebClient().get()
                .uri(resource.getEndpoint())
                .header(SUBSCRIPTION_KEY_HEADER, resource.getApiKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(typeRef)
                .block();
    }
}
