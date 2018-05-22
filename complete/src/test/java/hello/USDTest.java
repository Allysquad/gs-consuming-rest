package hello;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class USDTest {

    private static final Logger logger = LogManager.getLogger();

    private static String PRICE = "8405.39";
    private static String VOLUME_24H = "5180290000.0";
    private static String MARKET_CAP = "143285212387.0";
    private static String PERCENT_CHANGE_1H = "-0.33";
    private static String PERCENT_CHANGE_24H = "-1.44";
    private static String PERCENT_CHANGE_7D = "-4.0";

    static final String USDRESPONSESTRING = "            \"USD\"= {\n" +
            "                \"price\"= " + PRICE + ", \n" +
            "                \"volume_24h\"= " + VOLUME_24H + ", \n" +
            "                \"market_cap\"= " + MARKET_CAP + ", \n" +
            "                \"percent_change_1h\"= " + PERCENT_CHANGE_1H + ", \n" +
            "                \"percent_change_24h\"= " + PERCENT_CHANGE_24H + ", \n" +
            "                \"percent_change_7d\"= " + PERCENT_CHANGE_7D + "\n" +
            "            }\n";

    static USD CreateUsdObject() {
        logger.info("Starting translator");
        USD uSD = new USD();
        uSD.setPrice(PRICE);
        uSD.setVolume_24h(VOLUME_24H);
        uSD.setMarket_cap(MARKET_CAP);
        uSD.setPercent_change_1h(PERCENT_CHANGE_1H);
        uSD.setPercent_change_24h(PERCENT_CHANGE_24H);
        uSD.setPercent_change_7d(PERCENT_CHANGE_7D);
        return uSD;
    }

    @Test
    public void translate() {
        USD Usd = CreateUsdObject();
        assertEquals(USDRESPONSESTRING, Usd.toString());
        assertEquals(PRICE, Usd.getPrice());
        assertEquals(VOLUME_24H, Usd.getVolume_24h());
        assertEquals(MARKET_CAP, Usd.getMarket_cap());
        assertEquals(PERCENT_CHANGE_1H, Usd.getPercent_change_1h());
        assertEquals(PERCENT_CHANGE_24H, Usd.getPercent_change_24h());
        assertEquals(PERCENT_CHANGE_7D, Usd.getPercent_change_7d());
    }

    private static class CreateUsdObject extends USD {
    }
}
