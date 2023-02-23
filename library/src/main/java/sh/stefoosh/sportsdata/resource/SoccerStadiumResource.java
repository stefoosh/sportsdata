package sh.stefoosh.sportsdata.resource;

import org.springframework.stereotype.Component;
import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoints.SOCCER_STADIUM_RESOURCE;

@Component
public class SoccerStadiumResource extends StadiumVenueResource {

    public SoccerStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getSoccerSubscriptionKey(), SOCCER_STADIUM_RESOURCE);
    }

    @Override
    public Class<SoccerVenue[]> getResponseBodyClass() {
        return SoccerVenue[].class;
    }
}
