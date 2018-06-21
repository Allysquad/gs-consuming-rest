package client.CoinMarketCapAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "Quotes")
@DiscriminatorValue("Quotes")
@JsonIgnoreProperties(ignoreUnknown = true)
//@PrimaryKeyJoinColumn(name = "uniqueQuotesId")
public class Quotes extends Wrapper {

    //    private int uniqueQuotesId;
    @ManyToOne(cascade = {CascadeType.ALL})
    private USD uSD;

    public Quotes() {
    }

//    int getUniqueQuotesId() {return uniqueQuotesId;}
//
//    void setUniqueQuotesId(int uniqueQuotesId) {this.uniqueQuotesId = uniqueQuotesId;}

    @JsonProperty("USD")
    public USD getUSD() {
        return uSD;
    }

    @JsonProperty("USD")
    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

    @Override
    public String toString() {
        return "        \"quotes\"= {\n" +
                uSD +
                "        }";
    }
}
