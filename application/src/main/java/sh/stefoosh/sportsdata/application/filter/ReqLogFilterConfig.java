package sh.stefoosh.sportsdata.application.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class ReqLogFilterConfig {
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(false);
//        filter.setIncludePayload(true);
//        filter.setMaxPayloadLength(10000);
//        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}
