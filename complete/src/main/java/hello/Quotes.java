package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quotes extends USD {

    private USD uSD;

    Quotes() {
    }

    @JsonProperty("USD")
    USD getUSD() {
        return uSD;
    }

    @JsonProperty("USD")
    void setUSD(USD uSD) {
        this.uSD = uSD;
    }

    public String toString() {
        return "        \"quotes\"= {\n" +
                uSD +
                "        }";
    }
}
