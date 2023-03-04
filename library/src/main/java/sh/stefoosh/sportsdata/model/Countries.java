package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Countries {

    @Field(name = "id")
    private int id;

    private String name;

    private String iso2;

    private String region;

    private String subregion;

    private String emoji;

    private String latitude;

    private String longitude;
}
