package sh.stefoosh.sportsdata.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sh.stefoosh.sportsdata.constants.Sport;
import sh.stefoosh.sportsdata.model.StadiumVenue;

@AllArgsConstructor
public abstract class StadiumVenueResource implements SportsDataResource {

    @Getter(onMethod = @__(@Override))
    private String apiKey;

    @Getter(onMethod = @__(@Override))
    private String endpoint;

    @Override
    public Class<StadiumVenue[]> getResponseBodyClass() {
        return StadiumVenue[].class;
    }
}