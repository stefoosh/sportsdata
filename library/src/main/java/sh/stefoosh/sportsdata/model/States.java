package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static sh.stefoosh.sportsdata.constants.JsonProperty.COUNTRY_CODE;
import static sh.stefoosh.sportsdata.constants.JsonProperty.COUNTRY_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.COUNTRY_NAME;
import static sh.stefoosh.sportsdata.constants.JsonProperty.STATE_CODE;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class States {

    @Field(name = "id")
    private int id;

    private String name;

    @Field(name = COUNTRY_ID)
    private int countryId;

    @Field(name = COUNTRY_CODE)
    private String countryCode;

    @Field(name = COUNTRY_NAME)
    private String countryName;

    @Field(name = STATE_CODE)
    private String stateCode;

    private String latitude;

    private String longitude;
}
