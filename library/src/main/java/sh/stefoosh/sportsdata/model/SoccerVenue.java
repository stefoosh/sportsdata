package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import static sh.stefoosh.sportsdata.constants.JsonProperty.*;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SoccerVenue implements StadiumVenue {

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
