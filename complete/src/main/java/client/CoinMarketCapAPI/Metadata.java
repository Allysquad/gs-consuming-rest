package client.CoinMarketCapAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "Metadata")
@DiscriminatorValue("Metadata")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata extends Wrapper {

    private String timestamp;
    private String error;

    public Metadata() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "    \"metadata\"= {\n" +
                "        \"timestamp\"= " + timestamp + ", \n" +
                "        \"error\"= " + error + "\n" +
                "    }\n";
    }
}

