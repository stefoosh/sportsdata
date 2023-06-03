package sh.stefoosh.sportsdata.model;

import sh.stefoosh.sportsdata.constants.Sport;

public interface Game {

    // This is awesome. The subclass' lombok accessor overrides and implements the method.
    Sport getSport();

    int getStadiumId();

    <T extends StadiumVenue> void setLocation(T stadiumVenue);
}
