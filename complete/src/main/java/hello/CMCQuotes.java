package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CMCQuotes {

    private USD usd;

    public CMCQuotes() {
    }

    public USD getUsd() {
        return this.usd;
    }

    public void setUsd(USD usd) {
        this.usd = usd;
    }

    @Override
    public String toString() {
        return "Quotes{" +
                usd +
                '}';
    }
}
