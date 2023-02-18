package sh.stefoosh.sportsdata.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class StadiumVenueResource implements SportsDataResource {

    @Getter(onMethod = @__(@Override))
    private String apiKey;

    @Getter(onMethod = @__(@Override))
    private String endpoint;
}
