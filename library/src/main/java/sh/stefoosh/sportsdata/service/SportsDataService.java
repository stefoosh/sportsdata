package sh.stefoosh.sportsdata.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import sh.stefoosh.sportsdata.model.StadiumVenue;
import sh.stefoosh.sportsdata.resource.MlbStadiumResource;
import sh.stefoosh.sportsdata.resource.SportsDataResource;

import java.util.Arrays;
import java.util.List;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class SportsDataService {

//    private static final Logger LOG = LoggerFactory.getLogger(SportsDataService.class);
    private final ServiceProperties serviceProperties;
    private final MlbStadiumResource mlbStadiumResource;

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private WebClient webClient;

    public SportsDataService(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
        this.mlbStadiumResource = new MlbStadiumResource(serviceProperties);
        setWebClient(WebClient.builder().baseUrl(serviceProperties.getSportsDataApiBaseUrl()).build());
    }

    public void setWebClientBaseUrl(String baseUrl) {
        setWebClient(WebClient.builder().baseUrl(baseUrl).build());
    }

    public List<StadiumVenue> getStadiumVenues() {
        return Arrays.asList(getResponseBody(mlbStadiumResource));
    }

    private <T extends SportsDataResource, G> G getResponseBody(T resource) {

        ParameterizedTypeReference<G> typeRef = ParameterizedTypeReference.forType(resource.getResponseBodyClass());

        return getWebClient().get()
                .uri(resource.getEndpoint())
                .header(serviceProperties.getApiAuthHeaderKey(), resource.getApiKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(typeRef)
                .block();
    }
}
