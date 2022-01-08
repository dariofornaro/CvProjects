package ForYouShipment.Storage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Persistance.JourneyFactory;

/**
 * Singleton class storing all informations.
 */
public class JourneyStorage implements Storage {

    // Items to save.
    private Set <JourneyInfo> Journeys;

    

    private JourneyStorage() {
        Journeys = Collections.synchronizedSet(new HashSet<>());
    }

    public Set<JourneyInfo> getJourneys() {
        return Journeys;
    }

    public JSONArray SaveContentToJSON() {
        JSONArray array = new JSONArray();

        for (JourneyInfo j : Journeys)
            array.put(JourneyFactory.JourneyToJSON(j));
        return array;
    }

    public void ReadContentFromJSON(JSONArray array) {
        Journeys = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            JourneyInfo j;
            try {
                j = JourneyFactory.JourneyFromJSON(obj);
                Journeys.add(j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String StorageName() {
        return "JourneyStorage";
    }


    /**
     * This method returns the number of Journeys to aprove
     * @return Number of Journeys waiting for aproval as int
     */
    public int countJourneysToApprove(){
        int i = 0;
        for (JourneyInfo j : instance.Journeys){
            if (j.getStatus().contains("Waiting"))
                i++;
        }
        return i;
    }
    /**
     * This method returns the number of aproved Journeys
     * @return Number of Journeys waiting for aproval as int
     */
    public int countJourneysApproved(){
        int i = 0;
        for (JourneyInfo j : instance.Journeys){
            if (j.getStatus().contains("Active"))
                i++;
        }
        return i;
    }

    private static JourneyStorage instance = null;

    public static JourneyStorage GetInstance() {
        if (instance == null) {
            instance = new JourneyStorage();
        }
        return instance;
    }
}
