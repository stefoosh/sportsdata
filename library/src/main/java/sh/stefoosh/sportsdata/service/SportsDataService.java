package sh.stefoosh.sportsdata.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import sh.stefoosh.sportsdata.model.MlbGame;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.model.NhlGame;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.resource.MlbGameResource;
import sh.stefoosh.sportsdata.resource.MlbStadiumResource;
import sh.stefoosh.sportsdata.resource.NhlGameResource;
import sh.stefoosh.sportsdata.resource.NhlStadiumResource;
import sh.stefoosh.sportsdata.resource.ResourceBase;
import sh.stefoosh.sportsdata.resource.SoccerStadiumResource;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class SportsDataService {

    private static final int MAX_IN_MEMORY_SIZE = 5 * 1024 * 1024;
    private static final Logger LOG = LoggerFactory.getLogger(SportsDataService.class);
    private static final ExchangeStrategies EXCHANGE_STRATEGIES = ExchangeStrategies.builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
            .build();

    @Autowired
    private ServiceProperties properties;

    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private WebClient webClient;

    public SportsDataService(final ServiceProperties serviceProperties) {
        setWebClient(WebClient.builder()
                .baseUrl(serviceProperties.getSportsDataApiBaseUrl())
                .exchangeStrategies(EXCHANGE_STRATEGIES)
                .build());
    }

    public final void setWebClientBaseUrl(final String baseUrl) {
        setWebClient(WebClient.builder()
                .baseUrl(baseUrl)
                .exchangeStrategies(EXCHANGE_STRATEGIES)
                .build());
    }

    public final List<MlbStadium> getMlbStadiums() {
        return getModels(new MlbStadiumResource(properties));
    }

    public final List<NhlArena> getNhlStadiums() {
        return getModels(new NhlStadiumResource(properties));
    }

    public final List<SoccerVenue> getSoccerStadiums() {
        return getModels(new SoccerStadiumResource(properties));
    }


    public final List<MlbGame> getMlbGames() {
        return getModels(new MlbGameResource(properties));
    }

    public final List<NhlGame> getNhlGames() {
        return getModels(new NhlGameResource(properties));
    }

    private <T> List<T> getModels(final ResourceBase upstreamResource) {
        List<T> upstreamModels = Arrays.asList(getUpstreamResponseBody(upstreamResource));
        if (upstreamModels.isEmpty()) {
            throw new NoSuchElementException(
                    String.format("%s returned an empty list of models", upstreamResource.getEndpoint()));
        }
        return upstreamModels;
    }

    private <T extends ResourceBase, R> R getUpstreamResponseBody(final T resource) {
        LOG.debug("Fetching upstream {}", resource.getEndpoint());

        ParameterizedTypeReference<R> typeRef = ParameterizedTypeReference.forType(resource.getResponseBodyClass());

        return getWebClient().get()
                .uri(resource.getEndpoint())
                .header(properties.getApiAuthHeaderKey(), resource.getApiKey())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(typeRef)
                .block();
    }
}
