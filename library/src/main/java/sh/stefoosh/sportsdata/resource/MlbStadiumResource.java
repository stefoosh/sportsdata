package sh.stefoosh.sportsdata.resource;

import org.springframework.stereotype.Component;
import sh.stefoosh.sportsdata.service.ServiceProperties;

@Component
public class MlbStadiumResource extends StadiumVenueResource {

    public static final String END_POINT = "/mlb/scores/json/Stadiums";

    public MlbStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getMlbSubscriptionKey(), END_POINT);
    }
}
