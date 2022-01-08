package ForYouShipment.Persistance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import ForYouShipment.Constants.Port;
import ForYouShipment.JourneySearch.CriteriaJID;
import ForYouShipment.Models.ContainerMeasurements;
import ForYouShipment.Models.JourneyInfo;
import ForYouShipment.Search.Criteria;
import ForYouShipment.Storage.JourneyStorage;

public class ContainerFactory {
    public static JSONObject ContainerToJSON(ContainerMeasurements container) {
        JSONObject obj = new JSONObject();
        obj.put("location", container.getLocation().toString());
        obj.put("id", container.getId());
        int i = 0;
        for (Map<String, String> measurement : container.getMeasurementsHistory()){
            final int i2 = i;
            measurement.forEach(((k,v) -> obj.put(k + i2, v)));
            i++;
        }
        obj.put("HistorySize", i);
        if (container.getJourney()==null){
            return obj;
        }

        obj.put("journeyId", container.getJourney().getId());
        
        return obj;
    }

    public static ContainerMeasurements ContainerFromJSON(JSONObject obj){
        ContainerMeasurements c = new ContainerMeasurements();

        c.setId(obj.getString("id"));
        for  (int i = 0; i < obj.getInt("HistorySize"); i++){
            Criteria<JourneyInfo> criteria = new CriteriaJID();
            JourneyInfo j = criteria.meetCriteria(new ArrayList<>(JourneyStorage.GetInstance().getJourneys()), obj.getString("JourneyID" + i)).get(0);
            c.addToJourneyHistory(j);
        }
        JSONtoHistory(c, obj);
        try {
            Port p = Port.ofString(obj.getString("location"));
            c.setLocation(p);
            c.setParameter("Latitude", p.getLatitude().toString());
            c.setParameter("Longitude", p.getLongitude().toString());
            
        } catch (Exception e) {
            return c;
        }
       
        try {
            Criteria<JourneyInfo> m = new CriteriaJID();
            String jID = obj.getString("journeyId");

            List<JourneyInfo> j2 = m.meetCriteria(new ArrayList<JourneyInfo>(JourneyStorage.GetInstance().getJourneys()),
                        jID);
            
            JourneyInfo j = j2.get(0);
            c.setJourney(j);
            c.setAvailableParameters(Arrays.asList("Latitude","Longitude","Temperature", "Humidity","Pressure","Time", "JourneyID"));
            
            return c;
        }
        catch (JSONException e) {
            return c;
        }
    }


    private static void JSONtoHistory(ContainerMeasurements c, JSONObject obj){
        for  (int i = 0; i < obj.getInt("HistorySize"); i++){
            Map<String,String> m = new HashMap<>();
            m.put("Temperature", obj.getString("Temperature" + i));
            m.put("Pressure", obj.getString("Pressure" + i));
            m.put("Latitude", obj.getString("Latitude" + i));
            m.put("Longitude", obj.getString("Longitude" + i));
            m.put("Humidity", obj.getString("Humidity" + i));
            m.put("Time", obj.getString("Time" + i));
            m.put("JourneyID", obj.getString("JourneyID" + i));
            c.setParameter("Latitude", obj.getString("Latitude" + i));
            c.setParameter("Longitude", obj.getString("Longitude" + i));
            c.saveMeasurements(m);
        }  
    }
}
