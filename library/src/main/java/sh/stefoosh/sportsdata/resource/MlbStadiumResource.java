package sh.stefoosh.sportsdata.resource;

import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoint.MLB_SCORES_JSON_STADIUMS;

public class MlbStadiumResource extends StadiumVenueResource {

    public MlbStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getMlbSubscriptionKey(), MLB_SCORES_JSON_STADIUMS);
    }

    @Override
    public Class<MlbStadium[]> getResponseBodyClass() {
        return MlbStadium[].class;
    }
}
