package client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "Metadata")
@DiscriminatorValue("Metadata")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata extends Wrapper {

    private String timestamp;
    private String error;

    Metadata() {
    }

    String getTimestamp() {
        return timestamp;
    }

    void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    String getError() {
        return error;
    }

    void setError(String error) {
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

