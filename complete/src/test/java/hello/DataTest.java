package hello;

import org.junit.Test;

import static hello.QuotesTest.CreateCMCQuotesObject;
import static hello.MetadataTest.CreateMetadataObject;
import static hello.MetadataTest.METADATARESPONSESTRING;
import static org.junit.Assert.assertEquals;


public class DataTest {

    private static String ID = "1";
    private static String NAME = "Bitcoin";
    private static String SYMBOL = "BTC";
    private static String WEBSITE_SLUG = "bitcoin";
    private static String RANK = "1";
    private static String CIRCULATING_SUPPLY = "17046825.0";
    private static String TOTAL_SUPPLY = "17046825.0";
    private static String MAX_SUPPLY = "21000000.0";
    private static String LAST_UPDATED = "1526941172";

    static final String DATARESPONSESTRING =
            "    \"data\"= {\n" +
                    "        \"id\"= 1, \n" +
                    "        \"name\"= \"Bitcoin\", \n" +
                    "        \"symbol\"= \"BTC\", \n" +
                    "        \"website_slug\"= \"bitcoin\", \n" +
                    "        \"rank\"= 1, \n" +
                    "        \"circulating_supply\"= 17046825.0, \n" +
                    "        \"total_supply\"= 17046825.0, \n" +
                    "        \"max_supply\"= 21000000.0, \n" +
                    "        \"quotes\"= {\n" +
                    "            \"USD\"= {\n" +
                    "                \"price\"= 8405.39, \n" +
                    "                \"volume_24h\"= 5180290000.0, \n" +
                    "                \"market_cap\"= 143285212387.0, \n" +
                    "                \"percent_change_1h\"= -0.33, \n" +
                    "                \"percent_change_24h\"= -1.44, \n" +
                    "                \"percent_change_7d\"= -4.0\n" +
                    "            }\n" +
                    "        }, \n" +
                    "        \"last_updated\"= 1526941172\n" +
                    "    }, \n";


    static Data CreateDataObject() {
        Data data = new Data();
        data.setId("1");
        data.setName("Bitcoin");
        data.setSymbol("BTC");
        data.setWebsite_slug("bitcoin");
        data.setRank("1");
        data.setCirculating_supply("17046825.0");
        data.setTotal_supply("17046825.0");
        data.setMax_supply("21000000.0");
        data.setQuotes(CreateCMCQuotesObject());
        data.setLast_updated("1526941172");
        data.setMetadata(CreateMetadataObject());
        return data;
    }

    @Test
    public void translate() {
        Data data = CreateDataObject();
        assertEquals(DATARESPONSESTRING, data.toString());
        assertEquals(ID, data.getId());
        assertEquals(NAME, data.getName());
        assertEquals(SYMBOL, data.getSymbol());
        assertEquals(WEBSITE_SLUG, data.getWebsite_slug());
        assertEquals(RANK, data.getRank());
        assertEquals(CIRCULATING_SUPPLY, data.getCirculating_supply());
        assertEquals(TOTAL_SUPPLY, data.getTotal_supply());
        assertEquals(MAX_SUPPLY, data.getMax_supply());
        assertEquals(LAST_UPDATED, data.getLast_updated());
        assertEquals(Metadata.class, data.getMetadata().getClass());
        assertEquals(Quotes.class, data.getQuotes().getClass());
    }
}
