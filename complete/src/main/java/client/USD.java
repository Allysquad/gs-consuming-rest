package client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "USD")
@DiscriminatorValue("USD")
@JsonIgnoreProperties(ignoreUnknown = true)
//@PrimaryKeyJoinColumn(name = "uniqueUSDId")
public class USD extends Wrapper {

    //    private int uniqueUSDId;
    private String price;
    private String volume_24h;
    private String market_cap;
    private String percent_change_1h;
    private String percent_change_24h;
    private String percent_change_7d;

    USD() {
    }

//    int getUniqueUSDId() {return uniqueUSDId;}
//
//    void setUniqueUSDId(int uniqueUSDId) {this.uniqueUSDId = uniqueUSDId;}


    String getPrice() {
        return price;
    }

    void setPrice(String price) {
        this.price = price;
    }

    String getVolume_24h() {
        return volume_24h;
    }

    void setVolume_24h(String volume_24h) {
        this.volume_24h = volume_24h;
    }

    String getMarket_cap() {
        return market_cap;
    }

    void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    String getPercent_change_1h() {
        return percent_change_1h;
    }

    void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    String getPercent_change_24h() {
        return percent_change_24h;
    }

    void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    String getPercent_change_7d() {
        return percent_change_7d;
    }

    void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    @Override
    public String toString() {
        return "            \"USD\"= {\n" +
                "                \"price\"= " + price + ", \n" +
                "                \"volume_24h\"= " + volume_24h + ", \n" +
                "                \"market_cap\"= " + market_cap + ", \n" +
                "                \"percent_change_1h\"= " + percent_change_1h + ", \n" +
                "                \"percent_change_24h\"= " + percent_change_24h + ", \n" +
                "                \"percent_change_7d\"= " + percent_change_7d + "\n" +
                "            }\n";
    }
}
