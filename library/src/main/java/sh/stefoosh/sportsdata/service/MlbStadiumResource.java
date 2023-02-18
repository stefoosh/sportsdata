package sh.stefoosh.sportsdata.service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MlbStadiumResource extends StadiumVenueResource {
    public MlbStadiumResource() {
        super(Props.MLB_API_SUBSCRIPTION_KEY, "/mlb/scores/json/Stadiums");
    }
}
