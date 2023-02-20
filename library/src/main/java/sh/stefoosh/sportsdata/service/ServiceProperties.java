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
	private String sportsDataApiBaseUrl;

	@NotBlank
	private String apiAuthHeaderKey;

	@NotBlank
	private String mlbSubscriptionKey;

	@NotBlank
	private String nhlSubscriptionKey;

	@NotBlank
	private String soccerSubscriptionKey;
}
