package sh.stefoosh.sportsdata.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("service")
public class ServiceProperties {
	private String url;
	private String header;
	private String mlbkey;
}
