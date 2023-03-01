package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import static sh.stefoosh.sportsdata.constants.JsonProperty.AWAY_TEAM_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.DATE_TIME;
import static sh.stefoosh.sportsdata.constants.JsonProperty.DAY;
import static sh.stefoosh.sportsdata.constants.JsonProperty.HOME_TEAM_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.STADIUM_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.STATUS;
import static sh.stefoosh.sportsdata.constants.JsonProperty.UPDATED;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class NhlGame implements Game {

    @JsonProperty(STATUS)
    private String status;

    @JsonProperty(DAY)
    private String day;

    @JsonProperty(DATE_TIME)
    private String dateTime;

    @JsonProperty(UPDATED)
    private String updated;

    @JsonProperty(AWAY_TEAM_ID)
    private int awayTeamId;

    @JsonProperty(HOME_TEAM_ID)
    private int homeTeamId;

    @JsonProperty(STADIUM_ID)
    private int stadiumId;
}
