package sh.stefoosh.sportsdata.resource;

import org.springframework.stereotype.Component;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoints.MLB_STADIUM_RESOURCE;

@Component
public class MlbStadiumResource extends StadiumVenueResource {

    public static final String END_POINT = MLB_STADIUM_RESOURCE;

    public MlbStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getMlbSubscriptionKey(), END_POINT);
    }
}
