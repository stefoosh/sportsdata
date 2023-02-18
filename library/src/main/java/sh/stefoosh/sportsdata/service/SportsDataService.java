package sh.stefoosh.sportsdata.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class SportsDataService {

//    private static final Logger LOG = LoggerFactory.getLogger(SportsDataService.class);
    private final ServiceProperties serviceProperties;

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private WebClient webClient;

    public SportsDataService(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;

        setWebClient(WebClient.builder().baseUrl(getSportsDataApiBaseUrl()).build());
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
                .header(serviceProperties.getApiAuthHeaderKey(), resource.getApiKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(typeRef)
                .block();
    }

    public String getApiAuthHeaderKey() {
        return serviceProperties.getApiAuthHeaderKey();
    }

    public String getSportsDataApiBaseUrl() {
        return serviceProperties.getSportsDataApiBaseUrl();
    }
}
