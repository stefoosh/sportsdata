package sh.stefoosh.sportsdata.constants;

public final class Endpoint {
    public static final String APPLICATION_JSON = "application/json";
    public static final String COUNTRIES = "countries";
    public static final String MLB_SCORES_JSON_GAMES = "/mlb/scores/json/Games/2023";
    public static final String MLB_SCORES_JSON_STADIUMS = "/mlb/scores/json/Stadiums";
    public static final String NHL_SCORES_JSON_GAMES = "/nhl/scores/json/Games/2023";
    public static final String NHL_SCORES_JSON_STADIUMS = "/nhl/scores/json/Stadiums";
    public static final String SOCCER_SCORES_JSON_VENUES = "/soccer/scores/json/Venues";

    public static final String STATES = "states";

    private Endpoint() {
        throw new UnsupportedOperationException(
                String.format("%s is a utility class and cannot be instantiated", this.getClass().getName()));
    }
}
