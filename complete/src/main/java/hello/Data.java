package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    private DataObject dataObject;

    public Data() {
    }

    public DataObject getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObject dataObject) {
        this.dataObject = dataObject;
    }

    public String toString(){
        return  dataObject.getId().toString() + "{" +
                dataObject +
                '}';
    }
}
