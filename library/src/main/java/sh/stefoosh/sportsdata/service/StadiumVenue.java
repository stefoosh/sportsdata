package sh.stefoosh.sportsdata.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class StadiumVenue {
    private String StadiumID;
    private String Name;
    private String City;
    private String State;
    private String Country;
    private double GeoLat;
    private double GeoLong;
}

