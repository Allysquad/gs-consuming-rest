package client;

import client.CoinMarketCapAPI.Data;
import client.CoinMarketCapAPI.Metadata;
import client.CoinMarketCapAPI.Wrapper;
import org.junit.Test;

import static client.DataTest.CreateDataObject;
import static client.DataTest.DATARESPONSESTRING;
import static client.MetadataTest.CreateMetadataObject;
import static client.MetadataTest.METADATARESPONSESTRING;
import static org.junit.Assert.*;

public class WrapperTest {

    private static String WRAPPERRESPONSESTRING = "{\n" + DATARESPONSESTRING + METADATARESPONSESTRING + "}";

    static Wrapper CreateWrapperObject() {
        Wrapper wrapper = new Wrapper();
        wrapper.setData(CreateDataObject());
        wrapper.setMetaData(CreateMetadataObject());
        return wrapper;
    }

    @Test
    public void translate() {
        Wrapper wrapper = CreateWrapperObject();
        assertEquals(WRAPPERRESPONSESTRING, wrapper.toString());
        assertEquals(Data.class, wrapper.getData().getClass());
        assertEquals(Metadata.class, wrapper.getMetadata().getClass());
    }
}