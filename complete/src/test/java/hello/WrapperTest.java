package hello;

import org.junit.Test;

import static hello.DataTest.CreateDataObject;
import static hello.DataTest.DATARESPONSESTRING;
import static hello.MetadataTest.CreateMetadataObject;
import static hello.MetadataTest.METADATARESPONSESTRING;
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