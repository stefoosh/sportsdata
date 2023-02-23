package sh.stefoosh.sportsdata.resource;

import org.springframework.stereotype.Component;
import sh.stefoosh.sportsdata.model.MlbStadium;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoints.MLB_STADIUM_RESOURCE;

@Component
public class MlbStadiumResource extends StadiumVenueResource {

    public MlbStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getMlbSubscriptionKey(), MLB_STADIUM_RESOURCE);
    }

    @Override
    public Class<MlbStadium[]> getResponseBodyClass() {
        return MlbStadium[].class;
    }
}
