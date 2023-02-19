package sh.stefoosh.sportsdata.resource;

public interface SportsDataResource {
     String getApiKey();
     String getEndpoint();
     <G> Class<G> getResponseBodyClass();
}
