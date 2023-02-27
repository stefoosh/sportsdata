package sh.stefoosh.sportsdata.resource;

import sh.stefoosh.sportsdata.model.SoccerVenue;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoint.SOCCER_SCORES_JSON_VENUES;

public class SoccerStadiumResource extends StadiumVenueResource {

    public SoccerStadiumResource(final ServiceProperties serviceProperties) {
        super(serviceProperties.getSoccerSubscriptionKey(), SOCCER_SCORES_JSON_VENUES);
    }

    @Override
    public final Class<SoccerVenue[]> getResponseBodyClass() {
        return SoccerVenue[].class;
    }
}
