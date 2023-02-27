package sh.stefoosh.sportsdata.constants;

public final class JsonProperty {
    public static final String VENUE_ID = "VenueId";
    public static final String STADIUM_ID = "StadiumID";
    public static final String NAME = "Name";
    public static final String CITY = "City";
    public static final String STATE = "State";
    public static final String COUNTRY = "Country";
    public static final String LATITUDE = "GeoLat";
    public static final String LONGITUDE = "GeoLong";
    public static final String CAPACITY = "Capacity";

    private JsonProperty() {
        throw new UnsupportedOperationException(
                String.format("%s is a utility class and cannot be instantiated", this.getClass().getName()));
    }
}
