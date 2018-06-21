package client.CoinMarketCapAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "WrapperMapperStorage")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class WrapperMapperStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String a11_id;
    private String a13_name;
    private String a12_symbol;
    @JsonIgnore
    private String website_slug;
    @JsonIgnore
    private String rank;
    private String a16_circ_supply;
    @JsonIgnore
    private String total_supply; //This is how they dealt with the market cap issue for coins with no max supply
    private String max_supply;
    @JsonIgnore
    private String last_updated;
    //Metadata
    private String a14_timestamp;
    //USD
    private String a15_price;
    private String a17_volume_24h;
    @JsonIgnore
    private String market_cap;
    private String a18_pchng1;
    private String a19_pchng24;
    private String a20_pchng7d;
    private Float a21_pricePCoin;
    private String a22_googResult;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getA11_id() {
        return a11_id;
    }

    public void setA11_id(String a11_id) {
        this.a11_id = a11_id;
    }

    public String getA13_name() {
        return a13_name;
    }

    public void setA13_name(String a13_name) {
        this.a13_name = a13_name;
    }

    public String getA12_symbol() {
        return a12_symbol;
    }

    public void setA12_symbol(String a12_symbol) {
        this.a12_symbol = a12_symbol;
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

    public String getA22_googResult() {
        return a22_googResult;
    }

    public void setA22_googResult(String a22_googResult) {
        this.a22_googResult = a22_googResult;
    }

    public String getA16_circ_supply() {
        return a16_circ_supply;
    }

    public void setA16_circ_supply(String a16_circ_supply) {
        this.a16_circ_supply = a16_circ_supply;
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

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getA14_timestamp() {
        return a14_timestamp;
    }

    public void setA14_timestamp(String a14_timestamp) {
        this.a14_timestamp = a14_timestamp;
    }

    public String getA15_price() {
        return a15_price;
    }

    public void setA15_price(String a15_price) {
        this.a15_price = a15_price;
    }

    public String getA17_volume_24h() {
        return a17_volume_24h;
    }

    public void setA17_volume_24h(String a17_volume_24h) {
        this.a17_volume_24h = a17_volume_24h;
    }

    public String getMarket_cap() {
        return market_cap;
    }

    public void setMarket_cap(String market_cap) {
        this.market_cap = market_cap;
    }

    public String getP_Change_1h() {
        return a18_pchng1;
    }

    public void setP_Change_1h(String p_change_1h) {
        this.a18_pchng1 = p_change_1h;
    }

    public String getP_Change_24h() {
        return a19_pchng24;
    }

    public void setP_Change_24h(String p_change_24h) {
        this.a19_pchng24 = p_change_24h;
    }

    public String getP_Change_7d() {
        return a20_pchng7d;
    }

    public void setP_Change_7d(String p_change_7d) {
        this.a20_pchng7d = p_change_7d;
    }

    public Float getA21_pricePCoin() {
        return a21_pricePCoin;
    }

    public void setA21_pricePCoin(Float a21_pricePCoin) {
        this.a21_pricePCoin = a21_pricePCoin;
    }
}
