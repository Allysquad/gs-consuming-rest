package client.GoogleAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MappedGoogleResponse {

    @JsonProperty("SearchInformation")
    private SearchInformation searchInformation;

    @Override
    public String toString() {
        return "" + searchInformation;
    }
}



