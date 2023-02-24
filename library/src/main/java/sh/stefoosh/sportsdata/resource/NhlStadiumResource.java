package sh.stefoosh.sportsdata.resource;

import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoint.NHL_SCORES_JSON_STADIUMS;

public class NhlStadiumResource extends StadiumVenueResource {

    public NhlStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getNhlSubscriptionKey(), NHL_SCORES_JSON_STADIUMS);
    }

    @Override
    public Class<NhlArena[]> getResponseBodyClass() {
        return NhlArena[].class;
    }
}
