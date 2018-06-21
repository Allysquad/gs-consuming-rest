package client.CoinMarketCapAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity(name = "Data")
@DiscriminatorValue("Data")
@JsonIgnoreProperties(ignoreUnknown = true)
//@PrimaryKeyJoinColumn(name = "uniqueDataId")
public class Data extends Wrapper {

    //    private int uniqueDataId;
    private String id;
    private String name;
    private String symbol;
    private String website_slug;
    private String rank;
    private String circulating_supply;
    private String total_supply;
    private String max_supply;
    @ManyToOne(cascade = {CascadeType.ALL})
    private Quotes quotes;
    private String last_updated;

    public Data() {
    }

//    int getUniqueDataId() {return uniqueDataId;}
//
//    void setUniqueDataId(int uniqueDataId) {this.uniqueDataId = uniqueDataId;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getWebsite_slug() {
        return website_slug;
    }

    public void setWebsite_slug(String website_slug) {
        this.website_slug = website_slug;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getCirculating_supply() {
        return circulating_supply;
    }

    public void setCirculating_supply(String circulating_supply) {
        this.circulating_supply = circulating_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(String max_supply) {
        this.max_supply = max_supply;
    }

    @JsonProperty("quotes")
    public Quotes getQuotes() {
        return quotes;
    }

    @JsonProperty("quotes")
    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    @Override
    public String toString() {
        return "    \"data\"= {\n" +
                "        \"id\"= " + id + ", \n" +
                "        \"name\"= \"" + name + "\", \n" +
                "        \"symbol\"= \"" + symbol + "\", \n" +
                "        \"website_slug\"= \"" + website_slug + "\", \n" +
                "        \"rank\"= " + rank + ", \n" +
                "        \"circulating_supply\"= " + circulating_supply + ", \n" +
                "        \"total_supply\"= " + total_supply + ", \n" +
                "        \"max_supply\"= " + max_supply + ", \n" +
                "" + quotes + ", \n" +
                "        \"last_updated\"= " + last_updated + "\n" +
                "    }, \n";

    }
}

