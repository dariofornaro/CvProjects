package ForYouShipment.Persistance;
import org.json.JSONObject;

import ForYouShipment.Constants.Port;
import ForYouShipment.Models.JourneyInfo;

public class JourneyFactory {
    public static JSONObject JourneyToJSON(JourneyInfo journey) {
        JSONObject obj = new JSONObject();
        obj.put("ContentType", journey.getContent_type());
        obj.put("Cargo", journey.getCargo());
        obj.put("jID", journey.getId());
        obj.put("Status", journey.getStatus());
        obj.put("Origin", journey.getOrigin().toString());
        obj.put("Destination", journey.getDestination().toString());
        
        
        for (String item : journey.getAllParameters())
            obj.put(item, journey.getParameter(item));

        return obj;
    }

    public static JourneyInfo JourneyFromJSON(JSONObject obj) throws Exception {
        JourneyInfo journey = new JourneyInfo(" ");
        
        journey.setId(obj.getString("jID"));
        journey.setCargo(obj.getString("Cargo"));
        journey.setContent_type(obj.getString("ContentType"));
        journey.setStatus(obj.getString("Status"));
        journey.setOrigin(Port.ofString(obj.getString("Origin")));
        journey.setDestination(Port.ofString(obj.getString("Destination")));
        
        for (String item : journey.getAllParameters())
            journey.setParameter(item, obj.getString(item));

        return journey;
    }
}
