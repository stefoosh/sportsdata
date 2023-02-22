package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StadiumVenue {
    @Id
    private String id;

    @JsonProperty("StadiumID")
    private int stadiumId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("City")
    private String city;

    @JsonProperty("State")
    private String state;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("GeoLat")
    private double latitude;

    @JsonProperty("GeoLong")
    private double longitude;

    @JsonProperty("Capacity")
    private int capacity;
}
