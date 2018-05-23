package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrapper {

    private Data data;
    private Metadata metadata;

    Wrapper() {
    }

    @JsonProperty("data")
    Data getData() {
        return data;
    }

    @JsonProperty("data")
    void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("metadata")
    Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    void setMetaData(Metadata metadata) {
        this.metadata = metadata;
    }

    public String toString() {
        return "\n{\n" + data + metadata + "}";
    }

}