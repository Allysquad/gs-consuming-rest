package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {

    private String timestamp;
    private Long num_cryptocurrencies;
    private String error;

    public Metadata() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getNum_cryptocurrencies() {
        return num_cryptocurrencies;
    }

    public void setNum_cryptocurrencies(Long num_cryptocurrencies) {
        this.num_cryptocurrencies = num_cryptocurrencies;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return  "timestamp=" + timestamp +
                ", num_cryptocurrencies=" + num_cryptocurrencies +
                ", error=" + error ;
    }
}

