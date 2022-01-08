package ForYouShipment.Storage;

import org.json.JSONArray;

/**
 * An interface expanding Storage items with a persistance layer.
 */
public interface Storage {
    /**
     * This function saves the data into the JSON array.
     * @return JSON array with the content of the storage.
     */
    public JSONArray SaveContentToJSON();

    /**
     * This function reads data from a JSON array.
     */
    public void ReadContentFromJSON(JSONArray array);

    /**
     * Name of the storage
     */
    public String StorageName();
}
