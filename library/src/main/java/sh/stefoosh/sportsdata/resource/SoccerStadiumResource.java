package sh.stefoosh.sportsdata.resource;

import org.springframework.stereotype.Component;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoint.SOCCER_SCORES_JSON_VENUES;

@Component
public class SoccerStadiumResource extends StadiumVenueResource {

    public SoccerStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getSoccerSubscriptionKey(), SOCCER_SCORES_JSON_VENUES);
    }

    @Override
    public Class<SoccerVenue[]> getResponseBodyClass() {
        return SoccerVenue[].class;
    }
}
