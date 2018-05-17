package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CMCTicker {

    private Data data;
    private Metadata metadata;

    public CMCTicker() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String toString() {
        return  "CMCTicker{" +
                "data=" + data +
                ", Metadata=" + metadata +
                '}';
    }
}

