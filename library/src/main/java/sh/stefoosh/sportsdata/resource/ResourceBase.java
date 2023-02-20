package sh.stefoosh.sportsdata.resource;

public interface ResourceBase {
     String getApiKey();
     String getEndpoint();
     <G> Class<G> getResponseBodyClass();
}
