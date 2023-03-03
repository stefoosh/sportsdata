package sh.stefoosh.sportsdata.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import static sh.stefoosh.sportsdata.constants.JsonProperty.AWAY_TEAM;
import static sh.stefoosh.sportsdata.constants.JsonProperty.AWAY_TEAM_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.DATE_TIME;
import static sh.stefoosh.sportsdata.constants.JsonProperty.DAY;
import static sh.stefoosh.sportsdata.constants.JsonProperty.HOME_TEAM;
import static sh.stefoosh.sportsdata.constants.JsonProperty.HOME_TEAM_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.ISO_8601_FORMAT;
import static sh.stefoosh.sportsdata.constants.JsonProperty.STADIUM_ID;
import static sh.stefoosh.sportsdata.constants.JsonProperty.STATUS;
import static sh.stefoosh.sportsdata.constants.JsonProperty.UPDATED;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MlbGame implements Game {

    @JsonProperty(STATUS)
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO_8601_FORMAT)
    @JsonProperty(DAY)
    private Date day;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO_8601_FORMAT)
    @JsonProperty(DATE_TIME)
    private Date dateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ISO_8601_FORMAT)
    @JsonProperty(UPDATED)
    private Date updated;

    @JsonProperty(AWAY_TEAM)
    private String awayTeam;

    @JsonProperty(HOME_TEAM)
    private String homeTeam;

    @JsonProperty(AWAY_TEAM_ID)
    private int awayTeamId;

    @JsonProperty(HOME_TEAM_ID)
    private int homeTeamId;

    @JsonProperty(STADIUM_ID)
    private int stadiumId;
}
