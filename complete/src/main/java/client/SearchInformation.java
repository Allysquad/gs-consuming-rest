package client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchInformation {

    private String totalResults;

    @JsonProperty("SearchInformation")
    private SearchInformation searchInformation;

    @Override
    public String toString() {
        return "" + totalResults;
    }
}

