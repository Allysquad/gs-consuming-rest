package client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DataType")
public class Wrapper {

    //    @Column(name = "uniqueWrapperId", unique = true, nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int uniqueWrapperId;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Data data;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Metadata metadata;

    Wrapper() {
    }

//    int getUniqueWrapperId() {
//        return uniqueWrapperId;
//    }
//
//    void setUniqueWrapperId(int uniqueWrapperId) {
//        this.uniqueWrapperId = uniqueWrapperId;
//    }

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