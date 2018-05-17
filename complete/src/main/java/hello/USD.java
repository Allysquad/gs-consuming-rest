package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class USD {

    private Float price;
    private Float volume_24h;
    private Float market_cap;
    private Float percent_change_1h;
    private Float percent_change_24h;
    private Float percent_change_7d;


    public USD() {
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(Float volume_24h) {
        this.volume_24h = volume_24h;
    }

    public Float getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(Float market_cap) {
        this.market_cap = market_cap;
    }

    public Float getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(Float percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public Float getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(Float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public Float getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(Float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    @Override
    public String toString() {
        return "USD{" +
                "price=" + price +
                ", market_cap=" + market_cap +
                ", market_cap=" + market_cap +
                ", percent_change_1h=" + percent_change_1h +
                ", percent_change_24h=" + percent_change_24h +
                ", percent_change_7d=" + percent_change_7d +
                '}';
    }
}