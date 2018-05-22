package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wrapper {

    private Data data;
    private Metadata metadata;

    Wrapper() {
    }

    Data getData() {
        return data;
    }

    void setData(Data data) {
        this.data = data;
    }

    Metadata getMetadata() {
        return metadata;
    }

    void setMetaData(Metadata metadata) {
        this.metadata = metadata;
    }

    public String toString() {
        return "{\n" + data + metadata + "}";
    }

}