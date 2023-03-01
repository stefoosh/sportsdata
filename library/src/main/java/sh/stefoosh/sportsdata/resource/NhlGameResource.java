package sh.stefoosh.sportsdata.resource;

import sh.stefoosh.sportsdata.model.NhlGame;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoint.NHL_SCORES_JSON_GAMES;

public class NhlGameResource extends GameResource {

    public NhlGameResource(final ServiceProperties serviceProperties) {
        super(serviceProperties.getNhlSubscriptionKey(), NHL_SCORES_JSON_GAMES);
    }

    @Override
    public final Class<NhlGame[]> getResponseBodyClass() {
        return NhlGame[].class;
    }
}
