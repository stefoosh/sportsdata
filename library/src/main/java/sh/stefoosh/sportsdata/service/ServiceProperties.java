package sh.stefoosh.sportsdata.service;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@ConfigurationProperties("service")
public class ServiceProperties {
    @NotBlank
    private String sportsDataApiBaseUrl = "http://localhost:9090";
    @NotBlank
    private String apiAuthHeaderKey = "stub-header-key";
    @NotBlank
    private String mlbSubscriptionKey = "mlbStubSTUB";
    @NotBlank
    private String nhlSubscriptionKey = "nhlStubSTUB";
    @NotBlank
    private String soccerSubscriptionKey = "soccerStubSTUB";
}
