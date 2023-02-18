package sh.stefoosh.sportsdata.service;

import org.springframework.stereotype.Component;

@Component
public class MlbStadiumResource extends StadiumVenueResource {

    public static final String END_POINT = "/mlb/scores/json/Stadiums";

    public MlbStadiumResource(ServiceProperties serviceProperties) {
        super(serviceProperties.getMlbSubscriptionKey(), END_POINT);
    }
}
