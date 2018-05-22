package hello;

import org.junit.Test;

import static hello.USDTest.CreateUsdObject;
import static hello.USDTest.USDRESPONSESTRING;
import static org.junit.Assert.*;

public class QuotesTest {

    private static final String CMCQUOTESRESPONSESTRING = "        \"quotes\"= {\n" +
            USDRESPONSESTRING +
            "        }";

    static Quotes CreateCMCQuotesObject() {
        Quotes cMCQuotes = new Quotes();
        cMCQuotes.setUSD(CreateUsdObject());
        return cMCQuotes;
    }

    @Test
    public void translate() {
        Quotes cMCQuotes = CreateCMCQuotesObject();
        assertEquals(CMCQUOTESRESPONSESTRING, cMCQuotes.toString());
        assertEquals(USD.class, cMCQuotes.getUSD().getClass());
    }
}