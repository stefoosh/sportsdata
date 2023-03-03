package sh.stefoosh.sportsdata.constants;

public final class JsonProperty {
    public static final String AWAY_TEAM_ID = "AwayTeamID";
    public static final String AWAY_TEAM = "AwayTeam";
    public static final String CAPACITY = "Capacity";
    public static final String CITY = "City";
    public static final String COUNTRY = "Country";
    public static final String DATE_TIME = "DateTime";
    public static final String DAY = "Day";
    public static final String HOME_TEAM_ID = "HomeTeamID";
    public static final String HOME_TEAM = "HomeTeam";
    public static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String LATITUDE = "GeoLat";
    public static final String LONGITUDE = "GeoLong";
    public static final String NAME = "Name";
    public static final String STADIUM_ID = "StadiumID";
    public static final String STATE = "State";
    public static final String STATUS = "Status";
    public static final String UPDATED = "Updated";
    public static final String VENUE_ID = "VenueId";

    private JsonProperty() {
        throw new UnsupportedOperationException(
                String.format("%s is a utility class and cannot be instantiated", this.getClass().getName()));
    }
}
