package client;

import client.CoinMarketCapAPI.Metadata;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MetadataTest {

    private static String TIMESTAMP = "1526941080";
    private static String ERROR = null;

    static final String METADATARESPONSESTRING =
            "    \"metadata\"= {\n" +
                    "        \"timestamp\"= " + TIMESTAMP + ", \n" +
                    "        \"error\"= " + ERROR + "\n" +
                    "    }\n";

    static Metadata CreateMetadataObject() {
        Metadata metadata = new Metadata();
        metadata.setTimestamp(TIMESTAMP);
        metadata.setError(ERROR);
        return metadata;
    }

    @Test
    public void translate() {
        Metadata metadata = CreateMetadataObject();
        assertEquals(METADATARESPONSESTRING, metadata.toString());
        assertEquals(TIMESTAMP, metadata.getTimestamp());
        assertEquals(ERROR, metadata.getError());
    }
}
