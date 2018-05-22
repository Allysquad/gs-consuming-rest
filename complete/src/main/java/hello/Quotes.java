package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quotes extends USD {

    private USD uSD;

    Quotes() {
    }

    USD getUSD() {
        return uSD;
    }

    void setUSD(USD uSD) {
        this.uSD = uSD;
    }

    public String toString() {
        return "        \"quotes\"= {\n" +
                uSD +
                "        }";
    }
}
