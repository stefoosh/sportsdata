package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
    private String sport;
    @Id
    private String id;
    private int StadiumID;
    private String Name;
    private String City;
    private String State;
    private String Country;
    private double GeoLat;
    private double GeoLong;
    private int Capacity;
}
