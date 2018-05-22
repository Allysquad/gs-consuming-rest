package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Metadata {

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

    public String toString() {
        return "    \"metadata\"= {\n" +
                "        \"timestamp\"= " + timestamp + ", \n" +
                "        \"error\"= " + error + "\n" +
                "    }\n";
    }
}

