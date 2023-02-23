package sh.stefoosh.sportsdata.resource;

import org.springframework.stereotype.Component;
import sh.stefoosh.sportsdata.model.NhlArena;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoints.NHL_STADIUM_RESOURCE;

@Component
public class NhlStadiumResource extends StadiumVenueResource {

    public NhlStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getNhlSubscriptionKey(), NHL_STADIUM_RESOURCE);
    }

    @Override
    public Class<NhlArena[]> getResponseBodyClass() {
        return NhlArena[].class;
    }
}
