package sh.stefoosh.sportsdata.model;

import sh.stefoosh.sportsdata.constants.Sport;

public interface Game {
    Sport getSport();

    int getStadiumId();

    <T extends StadiumVenue> void setLocation(T stadiumVenue);
}
