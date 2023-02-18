package sh.stefoosh.sportsdata.service;

public interface SportsDataResource {
     String getApiKey();
     String getEndpoint();
     <G> Class<G> getResponseBodyClass();
}
