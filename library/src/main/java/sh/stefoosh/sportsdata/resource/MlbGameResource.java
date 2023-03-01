package sh.stefoosh.sportsdata.resource;

import sh.stefoosh.sportsdata.model.MlbGame;
import sh.stefoosh.sportsdata.service.ServiceProperties;

import static sh.stefoosh.sportsdata.constants.Endpoint.MLB_SCORES_JSON_GAMES;

public class MlbGameResource extends GameResource {
    public MlbGameResource(final ServiceProperties serviceProperties) {
        super(serviceProperties.getMlbSubscriptionKey(), MLB_SCORES_JSON_GAMES);
    }

    @Override
    public final Class<MlbGame[]> getResponseBodyClass() {
        return MlbGame[].class;
    }
}
