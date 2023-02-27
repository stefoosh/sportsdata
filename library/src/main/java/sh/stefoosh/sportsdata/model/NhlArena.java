package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import static sh.stefoosh.sportsdata.constants.JsonProperty.CAPACITY;
import static sh.stefoosh.sportsdata.constants.JsonProperty.CITY;
import static sh.stefoosh.sportsdata.constants.JsonProperty.COUNTRY;
import static sh.stefoosh.sportsdata.constants.JsonProperty.LATITUDE;
import static sh.stefoosh.sportsdata.constants.JsonProperty.LONGITUDE;
import static sh.stefoosh.sportsdata.constants.JsonProperty.NAME;
import static sh.stefoosh.sportsdata.constants.JsonProperty.STADIUM_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.STATE;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NhlArena implements StadiumVenue {

    @JsonProperty(STADIUM_ID)
    private int stadiumId;

    @JsonProperty(NAME)
    private String name;

    @JsonProperty(CITY)
    private String city;

    @JsonProperty(STATE)
    private String state;

    @JsonProperty(COUNTRY)
    private String country;

    @JsonProperty(LATITUDE)
    private double latitude;

    @JsonProperty(LONGITUDE)
    private double longitude;

    @JsonProperty(CAPACITY)
    private int capacity;
}
