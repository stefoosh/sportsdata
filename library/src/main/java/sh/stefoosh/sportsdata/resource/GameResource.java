package sh.stefoosh.sportsdata.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class GameResource implements ResourceBase {
    @Getter(onMethod = @__(@Override))
    private String apiKey;

    @Getter(onMethod = @__(@Override))
    private String endpoint;
}
